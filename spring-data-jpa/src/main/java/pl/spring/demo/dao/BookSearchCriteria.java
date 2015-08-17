package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.to.SearchCriteria;

public interface BookSearchCriteria {

	public List<BookEntity> findBySearchCriteria(SearchCriteria sc);
}
