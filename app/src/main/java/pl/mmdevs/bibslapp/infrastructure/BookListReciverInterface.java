package pl.mmdevs.bibslapp.infrastructure;

import java.util.List;

import pl.mmdevs.bibslapp.model.Book;

/**
 * Created by marek on 03.07.18.
 */

public interface BookListReciverInterface {

    void setBookList(List<Book> books);

    String getSearchString();

    void setError(Exception e);
}
