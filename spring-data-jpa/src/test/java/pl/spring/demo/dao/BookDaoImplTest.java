package pl.spring.demo.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class BookDaoImplTest {

    @Autowired
    private BookDao bookDao;
    
    @Test
	public void bookWithNullIdSavedWithNotNullId() {
		// given
		BookEntity book = new BookEntity();
		// when
		BookEntity result = bookDao.save(book);
		// then
		assertNotNull(result.getId().longValue());
	}

    @Test(expected = BookNotNullIdException.class)
    public void bookWithNotNullThrowsNotNullIdException() {
    	// given
    	BookEntity book = new BookEntity();
    	book.setId(2L);
    	// when
    	bookDao.save(book);
    	// then
    	fail("Should've thrown a BookNotNullIdException");
    }
    

}
