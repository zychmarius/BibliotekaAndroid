package pl.mmdevs.bibslapp.model;

/**
 * Created by marek on 01.07.18.
 */

public class Book {

    private String id;

    private String name;

    public Book(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
