package pl.spring.demo.web.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.spring.demo.service.BookSearchService;
import pl.spring.demo.to.BookTo;
import pl.spring.demo.to.SearchCriteria;

@Controller
public class SearchController {
    @Autowired
    private BookSearchService bookSearchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchBooks(Map<String, Object> params,
    		@RequestParam(value = "bookTitle", required = false) String bookTitle,
    		@RequestParam(value = "bookAuthor", required = false) String bookAuthor,
    		@RequestParam(value = "libraryName", required = false) String libraryName) {
    	final List<BookTo> results =
    			bookSearchService.findBySearchCriteria(bookTitle, bookAuthor, libraryName);
    	System.out.println(results.size());
    	params.put("books", results);
    	params.put("criteria", new SearchCriteria(bookTitle, bookAuthor, libraryName));
    	return "search";
    }

//    @RequestMapping(value = "/search", method = RequestMethod.GET)
//    public String searchHome(Map<String, Object> params) {
//    	final List<BookTo> results =
//    			bookSearchService.findBySearchCriteria(null, null, null);
//    	params.put("books", results);
//    	return "search";
//    }
}
