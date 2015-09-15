package pl.selenium.library.test;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

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
	public void testShouldCheckIfAuthorsFirstNameIsRequired() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setLastName("surname");
		authorModal.clickAddAuthorButton();
		// then
		assertTrue(authorModal.hasError());
	}

	@Test
	public void testShouldCheckIfAuthorsLastNameIsRequired() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setFirstName("name");
		authorModal.clickAddAuthorButton();
		// then
		assertTrue(authorModal.hasError());
	}

	@Test
	public void testShouldCheckIfTitleIsRequiredWhenAuthorsAreGiven() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setFirstName("name");
		authorModal.setLastName("surname");
		authorModal.clickAddAuthorButton();
		// if
		assumeFalse(authorModal.hasError());
		// endif
		bookModal.clickSaveBookButton();
		// then
		assertTrue(bookModal.getBookTitle().isEmpty());
		assertTrue(bookModal.hasError());
	}

	@Test
	public void testShouldCheckIfAddedAuthorsFirstNameWasNotDeleted() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		bookModal.setBookTitle("book title");
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setFirstName("name");
		authorModal.setLastName("surname");
		authorModal.clickAddAuthorButton();
		// if
		assumeFalse(authorModal.hasError());
		// endif
		bookModal.setAuthorsFirstName(0, "");
		bookModal.clickSaveBookButton();
		// then
		assertTrue(bookModal.hasError());
	}

	@Test
	public void testShouldCheckIfAddedAuthorsLastNameWasNotDeleted() {
		// given
		AddBookModal bookModal = listBooksPage.clickAddBookButton();
		bookModal.setBookTitle("book title");
		AddAuthorModal authorModal = bookModal.clickAddAuthorButton();
		// when
		authorModal.setFirstName("name");
		authorModal.setLastName("surname");
		// if
		assumeFalse(authorModal.hasError());
		// endif
		authorModal.clickAddAuthorButton();
		bookModal.setAuthorsLastName(0, "");
		bookModal.clickSaveBookButton();
		// then
		assertTrue(bookModal.hasError());
	}

}
