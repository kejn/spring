package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookMapper {

	public BookEntity toBookEntity(BookTo bookTo) {
		BookEntity bookEntity = new BookEntity(bookTo.getId(), bookTo.getTitle(), null);
		if(bookTo.getAuthors() != null) {
			bookEntity.setAuthors(authorsToList(bookTo.getAuthors()));
		}
		return bookEntity;
	}

	public BookTo toBookTo(BookEntity bookEntity) {
		BookTo bookTo = new BookTo(bookEntity.getId(), bookEntity.getTitle(), null);
		bookTo.setAuthors(authorsToString(bookEntity.getAuthors()));
		return bookTo;
	}
	
	public List<BookEntity> toBookEntityList(List<BookTo> bookToList) {
		List<BookEntity> bookEntityList = new ArrayList<>();
		for (BookTo bookTo : bookToList) {
			bookEntityList.add(toBookEntity(bookTo));
		}
		return bookEntityList;
	}

	public List<BookTo> toBookToList(List<BookEntity> bookEntityList) {
		List<BookTo> bookToList = new ArrayList<>();
		for (BookEntity bookEntity : bookEntityList) {
			bookToList.add(toBookTo(bookEntity));
		}
		return bookToList;
	}

	private List<AuthorTo> authorsToList(String bookToAuthors) {
		List<AuthorTo> authorsList = new ArrayList<>();
		String[] authors = bookToAuthors.split(",");
		for (String author : authors) {
			List<String> firstAndLastName = new ArrayList<>(Arrays.asList(author.split("\\s")));
			String firstName = new String();
			String lastName = new String();
			while(firstAndLastName.size() > 1) {
				firstName += firstAndLastName.remove(0);
			}
			if(firstAndLastName.size() > 0) {
				lastName = firstAndLastName.remove(0);
			}
			authorsList.add(new AuthorTo(new Long(1L), firstName, lastName));
		}
		return authorsList;
	}

	private String authorsToString(List<AuthorTo> bookEntityAuthors) {
		String authors = new String();
		for (AuthorTo author : bookEntityAuthors) {
			authors += author.getFirstName() + " " + author.getLastName() + ",";
		}
		return authors.substring(0,authors.length()-1);
	}
	
	
}
