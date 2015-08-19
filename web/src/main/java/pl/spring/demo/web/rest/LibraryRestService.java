package pl.spring.demo.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.spring.demo.service.LibraryService;
import pl.spring.demo.to.LibraryTo;

@Controller
@ResponseBody
public class LibraryRestService {

    @Autowired
    private LibraryService libraryService;

    @RequestMapping(value = "/library", method = RequestMethod.POST)
    public LibraryTo saveLibrary(@RequestBody LibraryTo library) {
        return libraryService.saveLibrary(library);
    }

    @RequestMapping(value = "/library", method = RequestMethod.DELETE)
    public void deleteLibrary(@RequestBody LibraryTo library) {
    	libraryService.deleteLibrary(library.getId());
    }
}
