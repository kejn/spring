package pl.spring.demo.to;

public class Book implements IdAware {
	private Long id;
    private String title;
    
    public Book() {
    }
    
    public Book(Long id, String title) {
    	this.id = id;
    	this.title = title; 
    }
    
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
