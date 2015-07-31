package pl.spring.demo.to;

import java.util.List;

public class BookEntity extends Book {
    private List<AuthorTo> authors;

    public BookEntity() {
    	super();
    }

    public BookEntity(Long id, String title, List<AuthorTo> authors) {
    	super(id,title);
    	this.authors = authors;
    }

    public List<AuthorTo> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorTo> authors) {
        this.authors = authors;
    }
}
