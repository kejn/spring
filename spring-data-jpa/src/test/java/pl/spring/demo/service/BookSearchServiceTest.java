package pl.spring.demo.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookSearchServiceTest {
	
	@Autowired
	private BookSearchService bookSearchService;

	@Test
	public void testShouldCallSearchByCriteria() {
		//given
		final long id = 1L;
		//when
		fail("Not yet implemented");
	}

}
