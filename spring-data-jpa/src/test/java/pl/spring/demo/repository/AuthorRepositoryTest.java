package pl.spring.demo.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.AuthorEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testShouldFindAllAuthors() {
        // when
        List<AuthorEntity> authorEntities = authorRepository.findAll();
        // then
        assertNotNull(authorEntities);
        assertFalse(authorEntities.isEmpty());
        assertEquals("Jan Kowalski", authorEntities.get(0).toString());
        assertEquals("Zbigniew Nowak", authorEntities.get(1).toString());
        assertEquals("Janusz Jankowski", authorEntities.get(2).toString());
    }
}
