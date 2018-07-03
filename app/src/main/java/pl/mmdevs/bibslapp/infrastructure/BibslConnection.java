package pl.mmdevs.bibslapp.infrastructure;

import android.support.annotation.NonNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by marek on 01.07.18.
 */

public class BibslConnection {

    private String host;

    private String sessionID;

    private static String INITIAL_URL = "cgi-bin/wspd_cgi.sh/wo_log.w?ln=jp";

    public BibslConnection(String host) {
        this.host = host;
    }

    public String bookSearch(String authorName) throws Exception {
        fetchSessionId();
        String searchUrl = createSearchQueryString(authorName);

        URL url = new URL(host+searchUrl);

        System.out.println(host+searchUrl);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        InputStream in = connection.getInputStream();

        StringBuilder resultBuilder = getStringBuilder(in);

        return resultBuilder.toString();
    }

    @NonNull
    private StringBuilder getStringBuilder(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder resultBuilder = new StringBuilder();
        String inputLine = "";

        while ((inputLine = reader.readLine()) != null) {
            resultBuilder.append(inputLine);
        }
        return resultBuilder;
    }

    public String getSessionID() {
        System.out.println(this.sessionID);
        return this.sessionID;
    }

    public String bookDetails(String bookId) throws Exception {
        fetchSessionId();
        String urlString = host+"/cgi-bin/wspd_cgi.sh/wo2_opbib.p?ID1="+sessionID+bookId;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.connect();

        return getStringBuilder(connection.getInputStream()).toString();
    }

    private void fetchSessionId() throws Exception {
        URL url = new URL(host+"/"+INITIAL_URL);

        System.out.println(host+"/"+INITIAL_URL);

        HttpURLConnection.setFollowRedirects(false);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(false);


        connection.connect();

        int responseStatus = connection.getResponseCode();

        boolean redirect = false;

        int status = connection.getResponseCode();
        redirect = isRedirect(status);


        if (!redirect) {
            throw new Exception("No redirect when fetching session id, status was" + status);
        }

        String newUrl = connection.getHeaderField("Location");

        String[] urlAndQuery = newUrl.split("\\?");

        if (urlAndQuery.length != 2) {
            throw new Exception("New url couldnt be splitted by ?");
        }

        String[] queryStringFragments = urlAndQuery[1].split("&");


        for (String urlFragment : queryStringFragments) {
            String[] keyValue = urlFragment.split("=");

            if (keyValue[0].equals("ID1")) {
                sessionID = keyValue[1];
            }
        }

        if (sessionID == null) {
            throw new Exception("No ID1 url provided");
        }

    }

    private boolean isRedirect(int status) {
        boolean redirect = false;
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }
        return redirect;
    }

    private String createSearchQueryString(String author) {
        String authorEncoded = author.replace(" ", "+");
        String queryTemplage = "/cgi-bin/wspd_cgi.sh/wo2_wszm21.p?ID1="+sessionID+
                "&ln=jp&RodzWysz=WZL&InSzIdx1=bs_tyt&TxtSz1="
                +authorEncoded+"&SzOp1=l&InSzIdx2=if100a&TxtSz2="
                +authorEncoded+"&SzOp2=i&InSzIdx3=as_talfab&TxtSz3=&SzOp3=i&InSzIdx4=ioprz&TxtSz4=&doktype=1&doktype=2&doktype=7&doktype=10&doktype=11&doktype=12&doktype=13&doktype=14&doktype=15&doktype=20&doktype=30&doktype=40&doktype=50&doktype=60&doktype=70&doktype=80&biblio=3&szukaj=Szukaj#idRowPozLst2";

        return queryTemplage;
    }
}
