package pl.spring.demo.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class LibraryServiceTest {

	@Autowired
	private LibraryService libraryService;
	
	@Test
	public void testShouldDeleteLibrary() {
		// given
		final long libraryId = 11L;
		libraryService.saveLibrary(new LibraryEntity(libraryId, "jedenasta"));
		final long numberOfLibraries = libraryService.findAllLibraries().size();
		// when
		libraryService.deleteLibrary(libraryId);
		// then
		assertTrue(numberOfLibraries > libraryService.findAllLibraries().size());
	}

}
