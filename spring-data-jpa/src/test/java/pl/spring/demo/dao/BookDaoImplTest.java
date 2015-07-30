package pl.spring.demo.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookTo;


public class BookDaoImplTest {

	@InjectMocks
	private BookDaoImpl bookDaoImpl;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookDaoImpl, "sequence", new Sequence());
		
	}

	@Test
	public void bookWithNullIdSavedWithNotNullId() {
		// given
		BookTo book = new BookTo(null, "title", "author");
		// when
		BookTo result = bookDaoImpl.save(book);
		// then
		assertEquals(7L, result.getId().longValue());
	}

	@Test
	public void bookWithNotNullIdSavedWithTheSameId() {
		// given
		long id = 99L;
		BookTo book = new BookTo(id, "title", "author");
		// when
		BookTo result = bookDaoImpl.save(book);
		// then
		assertEquals(id, result.getId().longValue());
	}

}
