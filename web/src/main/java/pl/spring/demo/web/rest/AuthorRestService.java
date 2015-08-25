package pl.spring.demo.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;

@RestController
@RequestMapping(value="/authors")
public class AuthorRestService {

    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/all-authors", method = RequestMethod.GET)
    public List<AuthorTo> findAllAuthors() {
        return authorService.findAllAuthors();
    }
}
