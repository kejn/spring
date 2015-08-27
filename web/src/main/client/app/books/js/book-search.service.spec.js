describe('book service', function () {
    'use strict';

    var $bookService;
    beforeEach(function () {
        module('app.main');
        module('app.books');
        inject(function(bookService) {
        	$bookService = bookService;
        });
    });

    it('search is defined', inject(function () {
        // then
        expect($bookService.search).toBeDefined();
    }));

    it('delete book is defined', inject(function () {
    	// then
    	expect($bookService.deleteBook).toBeDefined();
    }));

    it('save book is defined', inject(function () {
    	// then
    	expect($bookService.saveBook).toBeDefined();
    }));
    
    it('calls bookRestService.search', inject(function($q, bookRestService) {
    	// given
    	var titlePrefix = 'a';
    	var response = { data: [{id: 2, title: 'abc'}]};
        var searchDeferred = $q.defer();
        spyOn(bookRestService, 'search').and.returnValue(searchDeferred.promise);
        // when
        $bookService.search(titlePrefix);
        searchDeferred.resolve(response);
    	// then
        expect(bookRestService.search).toHaveBeenCalledWith(titlePrefix);
    }));

    it('calls bookRestService.deleteBook', inject(function($q, bookRestService) {
    	// given
    	var bookId = 1;
    	var deleteDeferred = $q.defer();
    	spyOn(bookRestService, 'deleteBook').and.returnValue(deleteDeferred.promise);
    	// when
    	$bookService.deleteBook(bookId);
    	deleteDeferred.resolve();
    	// then
    	expect(bookRestService.deleteBook).toHaveBeenCalledWith(bookId);
    }));
    
    it('calls bookRestService.saveBook', inject(function($q, bookRestService) {
    	// given
    	var book = {id: null, title: 'abc'};
    	var response = { data: {id: 1, title: 'abc'}};
    	var saveDeferred = $q.defer();
    	spyOn(bookRestService, 'saveBook').and.returnValue(saveDeferred.promise);
    	// when
    	$bookService.saveBook(book);
    	saveDeferred.resolve(response);
    	// then
    	expect(bookRestService.saveBook).toHaveBeenCalledWith(book);
    }));
});
