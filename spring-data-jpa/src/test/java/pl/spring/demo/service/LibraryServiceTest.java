package pl.spring.demo.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.LibraryTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;
	@Autowired
	private BookService bookService;
	
	@Test
	public void testShouldDeleteLibrary() {
		// given
		final long libraryId = 11L;
		libraryService.saveLibrary(new LibraryTo(libraryId, "jedenasta"));
		final long numberOfLibraries = libraryService.findAllLibraries().size();
		// when
		libraryService.deleteLibrary(libraryId);
		// then
		assertTrue(numberOfLibraries > libraryService.findAllLibraries().size());
	}
	
	@Test
	public void testShouldDeleteAllBooksInLibraryWhenItIsDeleted() {
		// given
		final long libraryId = 3L;
		
		long noOfBooksBefore = bookService.findAllBooks().size();
		// when
		libraryService.deleteLibrary(libraryId);
		long noOfBooksAfter = bookService.findAllBooks().size();
		// then
		assertTrue(noOfBooksBefore > noOfBooksAfter);
	}
}
