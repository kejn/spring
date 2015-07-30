package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
	private BookEntity bookEntity;
	private BookTo bookTo;

	public BookEntity convert2BookEntity(BookTo bookTo) {
		bookEntity = new BookEntity(bookTo.getId(), bookTo.getTitle(), null);
		setAuthors(bookTo.getAuthors());
		return bookEntity;
	}

	private void setAuthors(String bookToAuthors) {
		List<AuthorTo> authorsList = new ArrayList<>();
		String[] authors = bookToAuthors.split(",");
		for (String author : authors) {
			String[] names = author.split("\\s");
			String firstName = names[0];
			String lastName = names[1];
			authorsList.add(new AuthorTo(new Long(1L), firstName, lastName));
		}
		bookEntity.setAuthors(authorsList);
	}

	public BookTo convert2BookTo(BookEntity bookEntity) {
		bookTo = new BookTo(bookEntity.getId(), bookEntity.getTitle(), null);
		setAuthors(bookEntity.getAuthors());
		return bookTo;
	}

	private void setAuthors(List<AuthorTo> bookEntityAuthors) {
		String authors = new String();

		for (AuthorTo author : bookEntityAuthors) {
			authors += author.getFirstName() + " " + author.getLastName() + ",";
		}
		bookTo.setAuthors(authors.substring(0,authors.length()-1));
	}
	
	
}
