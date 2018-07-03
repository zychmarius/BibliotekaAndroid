package pl.mmdevs.bibslapp.application;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import pl.mmdevs.bibslapp.infrastructure.BookListReciverInterface;
import pl.mmdevs.bibslapp.infrastructure.BooksApi;
import pl.mmdevs.bibslapp.model.Book;

/**
 * Created by marek on 03.07.18.
 */

public class SearchBooksTask extends AsyncTask<Void, Void, List<Book>> {

    private BooksApi booksApi;

    private BookListReciverInterface reciver;


    public SearchBooksTask(BooksApi booksApi, BookListReciverInterface reciver) {
        this.booksApi = booksApi;
        this.reciver = reciver;
    }

    @Override
    protected List<Book> doInBackground(Void... params) {

        List<Book> books = new ArrayList<>();
        try {
            System.out.println("Szukam: " + reciver.getSearchString());
            books =  booksApi.searchBooks(reciver.getSearchString());
        } catch (Exception e) {
            reciver.setError(e);
        } finally {
            return books;
        }
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        reciver.setBookList(books);
    }
}
