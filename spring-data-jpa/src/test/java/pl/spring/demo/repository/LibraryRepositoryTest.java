package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {

	@Autowired
	private LibraryRepository libraryRepository;
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testShouldFindLibraryById() {
		// given
		final long libraryId = 1L;
		// when
		LibraryEntity libraryEntity = libraryRepository.findOne(libraryId);
		// then
		assertNotNull(libraryEntity);
		assertEquals("Oui bibliotheka", libraryEntity.getName());
	}

	@Test
	public void testShouldFindLibraryByName() {
		// given
		final String libraryName = "oui";
		// when
		List<LibraryEntity> librariesEntity = libraryRepository.findLibraryByName(libraryName);
		// then
		assertNotNull(librariesEntity);
		assertFalse(librariesEntity.isEmpty());
		assertEquals("Oui bibliotheka", librariesEntity.get(0).getName());
	}

	@Test
	public void testShouldDeleteLibrary() {
		// given
		final long libraryId = 11L;
		libraryRepository.save(new LibraryEntity(libraryId, "czecia"));
		final long numberOfLibraries = libraryRepository.count();
		// when
		libraryRepository.delete(libraryId);
		// then
		assertTrue(numberOfLibraries > libraryRepository.count());
	}

	@Test
	public void testShouldDeleteAllBooksInLibraryWhenItIsDeleted() {
		// given
		final long libraryId = 11L;
		LibraryEntity libraryEntity = new LibraryEntity(libraryId, "czecia");
		libraryRepository.save(libraryEntity);

		BookEntity book = new BookEntity(90L, "asd");
		book.setLibrary(libraryEntity);
		bookRepository.save(book);
		book.setId(100L);
		bookRepository.save(book);
		
		long noOfBooksBefore = bookRepository.count();
		// when
		libraryRepository.delete(libraryId);
		long noOfBooksAfter = bookRepository.count();
		// then
		assertTrue(noOfBooksBefore > noOfBooksAfter);
	}

}
