package pl.spring.demo.web.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class AuthorRestServiceTest {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        Mockito.reset(authorService);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void testShouldCallAuthorService() throws Exception {
        // given
        final AuthorTo author1 = new AuthorTo(1L, "Author", "1");
        final AuthorTo author2 = new AuthorTo(2L, "Author", "2");

        Mockito.when(authorService.findAllAuthors()).thenReturn(Arrays.asList(author1, author2));

        // when
        ResultActions response = this.mockMvc.perform(get("/authors/all-authors")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));
        // then
        Mockito.verify(authorService).findAllAuthors();

        response.andExpect(status().isOk())

                .andExpect(jsonPath("[0].id").value(author1.getId().intValue()))
                .andExpect(jsonPath("[0].firstName").value(author1.getFirstName()))
                .andExpect(jsonPath("[0].lastName").value(author1.getLastName()))

                .andExpect(jsonPath("[1].id").value(author2.getId().intValue()))
                .andExpect(jsonPath("[1].firstName").value(author2.getFirstName()))
                .andExpect(jsonPath("[1].lastName").value(author2.getLastName()));
    }
}
