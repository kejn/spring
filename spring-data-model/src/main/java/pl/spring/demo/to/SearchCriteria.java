package pl.spring.demo.to;

public class SearchCriteria {
	
	private String title;
	private String author;
	private String libraryName;
	
	public SearchCriteria(String title, String author, String libraryName) {
		this.title = title;
		this.author = author;
		this.libraryName = libraryName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLibraryName() {
		return libraryName;
	}
	
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	
}
