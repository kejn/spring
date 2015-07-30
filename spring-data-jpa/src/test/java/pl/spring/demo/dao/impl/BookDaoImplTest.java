package pl.spring.demo.dao.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.to.BookTo;

public class BookDaoImplTest {

	@InjectMocks
	private BookDaoImpl bookDao;

	@Before
	public void setUpt() {
		MockitoAnnotations.initMocks(this);
		Whitebox.setInternalState(bookDao, "sequence", new Sequence());
	}

	@Test
	public void bookWithNullIdSavedWithNotNullId() {
		// given
		BookTo book = new BookTo(null, "title", "author");
		// when
		BookTo result = bookDao.save(book);
		// then
		assertEquals(7L, result.getId().longValue());
	}

}
