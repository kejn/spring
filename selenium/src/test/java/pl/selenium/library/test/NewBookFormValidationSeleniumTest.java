package pl.selenium.library.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import pl.selenium.library.AbstractSelenium;
import pl.selenium.library.pages.AddAuthorModal;
import pl.selenium.library.pages.AddBookModal;
import pl.selenium.library.pages.ListBooksPage;

public class NewBookFormValidationSeleniumTest extends AbstractSelenium {

	private ListBooksPage listBooksPage;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		listBooksPage = openWelcomePage().clickListBooks();
	}

	@Test
	public void testShouldCheckIfAuthorsAreRequired() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		// when
		bookModal.setBookTitle("book title");
		bookModal.clickSaveBookButton();
		// then
		assertTrue(bookModal.hasError());
	}

	@Test
	public void testShouldCheckIfTitleIsRequired() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setFirstName("name");
		authorModal.setLastName("surname");
		authorModal.clickAddAuthorButton();
		assertFalse(authorModal.hasError());
		bookModal.clickSaveBookButton();
		// then
		assertTrue(bookModal.getBookTitle().isEmpty());
		assertTrue(bookModal.hasError());
	}

}
