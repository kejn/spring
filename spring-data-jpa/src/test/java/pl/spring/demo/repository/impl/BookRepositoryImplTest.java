package pl.spring.demo.repository.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.repository.BookRepository;
import pl.spring.demo.to.SearchCriteria;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "../CommonRepositoryTest-context.xml")
public class BookRepositoryImplTest {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void testShouldFindAllBooksNoCriteria() {
		// given
		SearchCriteria searchCriteria = new SearchCriteria(null, null, null);
		// when
		List<BookEntity> bookEntities = bookRepository.findBySearchCriteria(searchCriteria);
		// then
		assertNotNull(bookEntities);
		assertFalse(bookEntities.isEmpty());
		assertEquals(bookRepository.findAll().size(), bookEntities.size());
	}

	@Test
    public void testShouldFindBookCriteriaTitle() {
        // given
        SearchCriteria searchCriteria = new SearchCriteria("Pierwsza książka", null, null);
        // when
        List<BookEntity> bookEntities = bookRepository.findBySearchCriteria(searchCriteria);
        // then
        assertNotNull(bookEntities);
        assertFalse(bookEntities.isEmpty());
        assertEquals("Pierwsza książka", bookEntities.get(0).getTitle());
    }

	@Test
	public void testShouldFindBooksCriteriaAuthor() {
		// given
		SearchCriteria searchCriteria = new SearchCriteria(null, "Janusz", null);
		// when
		List<BookEntity> bookEntities = bookRepository.findBySearchCriteria(searchCriteria);
		// then
		assertNotNull(bookEntities);
		assertFalse(bookEntities.isEmpty());
		assertEquals("Trzecia książka", bookEntities.get(0).getTitle());
	}
	
	@Test
	public void testShouldFindBookCriteriaLibrary() {
		// given
		SearchCriteria searchCriteria = new SearchCriteria(null, null, "Zwei");
		// when
		List<BookEntity> bookEntities = bookRepository.findBySearchCriteria(searchCriteria);
		// then
		assertNotNull(bookEntities);
		assertFalse(bookEntities.isEmpty());
		assertEquals("Druga książka", bookEntities.get(0).getTitle());
	}
}
