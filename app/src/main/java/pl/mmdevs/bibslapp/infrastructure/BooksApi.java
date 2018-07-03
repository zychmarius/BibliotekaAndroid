package pl.mmdevs.bibslapp.infrastructure;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pl.mmdevs.bibslapp.model.Book;
import pl.mmdevs.bibslapp.model.BookDetails;

/**
 * Created by marek on 01.07.18.
 */

public class BooksApi {

    private BibslConnection connection;

    public BooksApi(BibslConnection connection) {
        this.connection = connection;
    }

    public List<Book> searchBooks(String authtor) throws Exception {

        String booksHtml = connection.bookSearch(authtor);
        return parseHtml(booksHtml);
    }

    public BookDetails getBookDetails(String bookId) throws Exception {
        String bookDetailsHtml = connection.bookDetails(bookId);

        return parseDetailsHtml(bookDetailsHtml);
    }

    private BookDetails parseDetailsHtml(String bookDetailsHtml) {
        return new BookDetails();
    }

    private List<Book> parseHtml(String booksHtml) {
        ArrayList<Book> books = new ArrayList();
        Document doc = Jsoup.parse(booksHtml);

        Elements rows = doc.getElementsByClass("row");

        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements rowChildren = row.getElementsByTag("td");
            Element bookDescriptionHref = rowChildren.get(4).getElementsByTag("a").get(0);

            String bookName = bookDescriptionHref.html();
            String bookAddress = bookDescriptionHref.attr("onclick");

            System.out.println("Book name ="+bookName);

            Pattern pattern = Pattern.compile("\'[^\']*\'");
            Matcher matcher = pattern.matcher(bookAddress);

            matcher.find();
            matcher.group();
            matcher.find();
            String lookup = matcher.group();

            System.out.println(lookup);

            Book book = new Book(lookup, bookName);
            books.add(book);
        }

        return books;
    }
}
