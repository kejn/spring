package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testShouldFindAllBooks() {
        // when
        List<BookTo> allBooks = bookService.findAllBooks();
        List<BookTo> allBooksByTitle = bookService.findBooksByTitle("");
        List<BookTo> allBooksByAuthor = bookService.findBooksByAuthor("");
        // then
        assertNotNull(allBooks);
        assertFalse(allBooks.isEmpty());
        assertEquals(6, allBooks.size());
        
        assertNotNull(allBooksByTitle);
        assertFalse(allBooksByTitle.isEmpty());
        assertEquals(6, allBooksByTitle.size());
        
        assertNotNull(allBooksByAuthor);
        assertFalse(allBooksByAuthor.isEmpty());
        assertEquals(6, allBooksByAuthor.size());
    }

    @Test
    public void testShouldFindBooksByTitle() {
        // given
        final String title = "Opium w rosole";
        // when
        List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
        // then
        assertNotNull(booksByTitle);
        assertFalse(booksByTitle.isEmpty());
    }

    @Test
    public void testShouldFindBooksByAuthor() {
    	// given
    	final String author = "Wi";
    	// when
    	List<BookTo> booksByTitle = bookService.findBooksByAuthor(author);
    	// then
    	assertNotNull(booksByTitle);
    	assertFalse(booksByTitle.isEmpty());
    }

    @Test(expected = BookNotNullIdException.class)
    public void testShouldThrowBookNotNullIdException() {
        // given
        final BookTo bookToSave = new BookTo();
        bookToSave.setId(22L);
        // when
        bookService.saveBook(bookToSave);
        // then
        fail("test should throw BookNotNullIdException");
    }

}
