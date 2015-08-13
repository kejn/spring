package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.spring.demo.service.LibraryService;
import pl.spring.demo.web.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class LibraryRestServiceTest {

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        Mockito.reset(libraryService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

//    @Test
//    public void testShouldCallLibraryService() throws Exception {
//        // given
//        final String libName = "testName";
//
//        final LibraryEntity libraryEntity1 = new LibraryEntity(1L, libName);
//        final LibraryEntity libraryEntity2 = new LibraryEntity(2L, libName);
//
//        Mockito.when(libraryService.findLibraryByName(libName)).thenReturn(Arrays.asList(bookTo1, bookTo2));
//
//        // when
//        ResultActions response = this.mockMvc.perform(get("/books-by-title?titlePrefix=" + bookTitle)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON));
//        // then
//        Mockito.verify(bookService).findBooksByTitle(bookTitle);
//
//        response.andExpect(status().isOk())
//
//                .andExpect(jsonPath("[0].id").value(bookTo1.getId().intValue()))
//                .andExpect(jsonPath("[0].title").value(bookTo1.getTitle()))
//                .andExpect(jsonPath("[0].authors").value(bookTo1.getAuthors()))
//
//                .andExpect(jsonPath("[1].id").value(bookTo2.getId().intValue()))
//                .andExpect(jsonPath("[1].title").value(bookTo2.getTitle()))
//                .andExpect(jsonPath("[1].authors").value(bookTo2.getAuthors()));
//    }

    @Test
    public void testShouldSaveLibrary() throws Exception {
        // given
        File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/libraryToSave.json");
        String json = FileUtils.readFileToString(file);
        // when
        ResultActions response = this.mockMvc.perform(post("/library")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json.getBytes()));
        // then
        response.andExpect(status().isOk());
    }

    @Test
    public void testShouldDeleteLibrary() throws Exception {
    	// given
        File file = FileUtils.getFileFromClasspath("classpath:pl/spring/demo/web/json/libraryToSave.json");
        String json = FileUtils.readFileToString(file);
    	// when
    	ResultActions response = this.mockMvc.perform(delete("/library")
    			.accept(MediaType.APPLICATION_JSON)
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(json.getBytes()));
    	// then
    	response.andExpect(status().isOk());
    	Mockito.verify(libraryService).deleteLibrary(Mockito.anyLong());
    }
    
    
}
