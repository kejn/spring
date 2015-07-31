package pl.spring.demo.mock;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.impl.BookDaoImpl;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookMapper;
import pl.spring.demo.to.BookTo;


public class BookDaoImplTest {
	@InjectMocks
	private BookDaoImpl bookDaoImpl;
	
	private BookMapper bookMapper;
	

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookDaoImpl, "sequence", new Sequence());
		bookMapper = new BookMapper();
	}

	@Test
	public void bookWithNotNullIdSavedWithTheSameId() {
		// given
		long id = 99L;
		BookEntity book = bookMapper.toBookEntity(new BookTo(id, "title", "author"));
		// when
		BookEntity result = bookDaoImpl.save(book);
		// then
		assertEquals(id, result.getId().longValue());
	}
	
}
