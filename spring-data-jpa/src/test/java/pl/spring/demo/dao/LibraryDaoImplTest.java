package pl.spring.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {

    @Autowired
    private LibraryDao libraryDao;

    @Test
    public void testShouldFindLibraryById() {
        // given
        final long libraryId = 1;
        // when
        LibraryEntity libraryEntity = libraryDao.findOne(libraryId);
        // then
        assertNotNull(libraryEntity);
        assertEquals("Oui bibliotheka", libraryEntity.getName());
    }

    @Test
    public void testShouldFindLibraryByName() {
        // given
        final String libraryName = "ou";
        // when
        List<LibraryEntity> librariesEntity = libraryDao.findLibraryByName(libraryName);
        // then
        assertNotNull(librariesEntity);
        assertFalse(librariesEntity.isEmpty());
        assertEquals("Oui bibliotheka", librariesEntity.get(0).getName());
    }
}
