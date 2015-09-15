package pl.selenium.library.test;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

import org.junit.Before;
import org.junit.Test;

import pl.selenium.library.AbstractSelenium;
import pl.selenium.library.pages.AddAuthorModal;
import pl.selenium.library.pages.AddBookModal;
import pl.selenium.library.pages.ListBooksPage;

public class BookOperationsSeleniumTest extends AbstractSelenium {

	private final String titlePrefix = "Pierwsza";

	private ListBooksPage listBooksPage;
	private AddBookModal bookModal;

	@Override
	@Before
	public void setUp() {
		super.setUp();
		listBooksPage = openWelcomePage().clickListBooks();
		// when
		listBooksPage.setTitlePrefix(titlePrefix);
		bookModal = listBooksPage.clickAddBookButton();
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		authorModal.setFirstName("name");
		authorModal.setLastName("surname");
		authorModal.clickAddAuthorButton();
		bookModal.clickSaveBookButton();
		assumeTrue(listBooksPage.alertSuccess());
	}
	
	@Test
	public void testShouldAddABook() {
		// then
		assertTrue(listBooksPage.countBooks() > 0);
	}

	@Test
	public void testShouldEditABook() {
		// given
		final int sizeBefore = listBooksPage.countBooks();
		final String newName = "newName"; 
		// when
		bookModal = listBooksPage.clickEditBookButton(0);
		bookModal.setAuthorsFirstName(0, newName);
		bookModal.clickSaveBookButton();
		// then
		assertTrue(listBooksPage.alertSuccess());
		assertEquals(sizeBefore, listBooksPage.countBooks());
	}

	@Test
	public void testShouldFindABookByTitlePrefix() {
		// when
		listBooksPage.clickSearchButton();
		// then
		assertTrue(listBooksPage.countBooks() > 0);
	}
	
	@Test
	public void testShouldDeleteABook() {
		// when
		listBooksPage.clickSearchButton();
		// if
		final int sizeBefore = listBooksPage.countBooks();
		assumeTrue(sizeBefore > 0);
		// endif
		listBooksPage.clickDeleteButton(0);
		// then
		assertTrue(listBooksPage.alertSuccess());
		assertTrue(listBooksPage.countBooks() < sizeBefore);
	}

}
