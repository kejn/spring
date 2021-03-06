package pl.spring.demo.mock;
/**
 * @COPYRIGHT (C) 2015 Schenker AG
 *
 * All rights reserved
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookMapper;
import pl.spring.demo.to.BookTo;

/**
 * TODO The class BookServiceImplTest is supposed to be documented...
 *
 * @author AOTRZONS
 */
public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	
//	@InjectMocks
	private BookMapper bookMapper;
	
	@Mock
	private BookDao bookDao;

	@Before
	public void setUpt() {
		bookMapper = new BookMapper();
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookService, "bookMapper", bookMapper);
	}

	@Test
	public void testShouldSaveBook() {
		// given
		BookTo book = new BookTo(null, "title", "author");
		Mockito.when(bookDao.save(Mockito.any(BookEntity.class))).thenAnswer(new Answer<BookEntity>() {
			@Override
			public BookEntity answer(InvocationOnMock invocation) throws Throwable {
				BookEntity param = (BookEntity) invocation.getArguments()[0];
				param.setId(11L);
				return param;
			}
		});
		// when
		BookTo result = bookService.saveBook(book);
		// then
		ArgumentCaptor<BookEntity> cappture = ArgumentCaptor.forClass(BookEntity.class);
		Mockito.verify(bookDao).save(cappture.capture());
		assertEquals(11L, result.getId().longValue());
		assertNotNull(cappture.getValue().getId());
		assertEquals(11L, cappture.getValue().getId().longValue());
	}
}
