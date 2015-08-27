describe('book rest service', function () {
    'use strict';

    var $bookRestService;
    var httpBackend;
    var context;
    beforeEach(function () {
        module('app.main');
        module('app.books');
        inject(function(bookRestService, $httpBackend, currentContextPath) {
        	$bookRestService = bookRestService;
        	httpBackend = $httpBackend;
        	context = currentContextPath.get();
        });
    });

    it('search is defined', inject(function () {
        // then
        expect($bookRestService.search).toBeDefined();
    }));

    it('delete book is defined', inject(function () {
    	// then
    	expect($bookRestService.deleteBook).toBeDefined();
    }));

    it('save book is defined', inject(function () {
    	// then
    	expect($bookRestService.saveBook).toBeDefined();
    }));
    
    it('calls bookRestService.search with GET', inject(function() {
    	// given
    	var titlePrefix = 'a';
    	var response = { data: [{id: 2, title: 'abc'}]};
        // when
    	httpBackend.expect('GET', context + 'rest/books/books-by-title?titlePrefix=' + titlePrefix).respond(response);
    	$bookRestService.search(titlePrefix);
    	// then
    	httpBackend.flush();
    }));

    it('calls bookRestService.deleteBook with DELETE', inject(function() {
    	// given
    	var bookId = 1;
    	// when
    	httpBackend.expect('DELETE', context + 'rest/books/book/' + bookId).respond();
    	$bookRestService.deleteBook(bookId);
    	// then
    	httpBackend.flush();
    }));
    
    it('calls bookRestService.saveBook with POST', inject(function() {
    	// given
    	var book = {id: null, title: 'abc'};
    	var response = { data: [{id: 1, title: 'abc'}]};
    	// when
    	httpBackend.expect('POST', context + 'rest/books/book/', book).respond(response);
    	$bookRestService.saveBook(book);
    	// then
    	httpBackend.flush();
    }));
});
