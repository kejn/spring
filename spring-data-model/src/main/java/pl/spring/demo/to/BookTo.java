package pl.spring.demo.to;

public class BookTo extends Book {
    private String authors;

    public BookTo() {
    	super();
    }

    public BookTo(Long id, String title, String authors) {
        super(id,title);
        this.authors = authors;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }
    
    @Override
    public String toString() {
    	return super.toString() + authors;
    }
}
