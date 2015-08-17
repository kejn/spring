package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.service.BookSearchService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.SearchCriteria;

@Service
@Transactional(readOnly = true)
public class BookSearchServiceImpl implements BookSearchService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookTo> findBySearchCriteria(String title, String author, String libraryName) {
		return BookMapper.map2To(bookRepository.findBySearchCriteria(
				new SearchCriteria(title, author, libraryName)));
	}
}
