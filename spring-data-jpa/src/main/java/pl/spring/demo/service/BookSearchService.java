package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.BookTo;

public interface BookSearchService {
	
	public List<BookTo> findBySearchCriteria(String title, String author, String libraryName);
}
