package pl.spring.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.LibraryTo;

@Controller
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/libraries", method = RequestMethod.GET)
    public String bookList(Map<String, Object> params) {
        final List<LibraryTo> allLibraries = libraryService.findAllLibraries();
        params.put("libraries", allLibraries);
        return "libraryList";
    }
}
