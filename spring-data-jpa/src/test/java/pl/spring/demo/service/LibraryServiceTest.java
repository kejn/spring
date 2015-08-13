package pl.spring.demo.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;
//	@Autowired
//	private BookService bookService;

//	@Test
//	public void testShouldFindLibraryById() {
//		// given
//		final long libraryId = 1L;
//		// when
//		LibraryEntity libraryEntity = libraryService.findOne(libraryId);
//		// then
//		assertNotNull(libraryEntity);
//		assertEquals("Oui bibliotheka", libraryEntity.getName());
//	}

	@Test
	public void testShouldDeleteLibrary() {
		// given
		final long libraryId = 1L;
//		libraryService.saveLibrary(new LibraryEntity(libraryId, "czecia"));
		final long numberOfLibraries = libraryService.findAllLibraries().size();
		// when
		libraryService.deleteLibrary(libraryId);
		// then
		assertTrue(numberOfLibraries > libraryService.findAllLibraries().size());
	}

//	@Test
//	public void testShouldDeleteAllBooksInLibraryWhenItIsDeleted() {
//		// given
//		final long libraryId = 3L;
//		LibraryEntity libraryEntity = new LibraryEntity(libraryId, "czecia");
//		libraryService.save(libraryEntity);
//
//		BookEntity book = new BookEntity(9L, "asd");
//		book.setLibrary(libraryEntity);
//		bookRepository.save(book);
//		book.setId(10L);
//		bookRepository.save(book);
//		
//		long noOfBooksBefore = bookRepository.count();
//		// when
//		libraryService.delete(libraryId);
//		long noOfBooksAfter = bookRepository.count();
//		System.out.println(noOfBooksBefore + " " + noOfBooksAfter);
//		// then
//		assertTrue(noOfBooksBefore > noOfBooksAfter);
//	}

}
