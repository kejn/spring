package pl.selenium.library.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import pl.selenium.library.AbstractSelenium;
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
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		bookModal.setBookTitle("book title");
		bookModal.clickSaveBookButton();
		assertTrue(bookModal.hasError());
	}

}
