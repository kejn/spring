describe('book-modal add-book-controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    var controller;
    var modalInstance;
    var book;
    beforeEach(inject(function ($rootScope, $controller) {
        $controller('BookSearchController', {$scope: $rootScope});
        modalInstance = {
        		close: jasmine.createSpy('modalInstance.close'),
        };
        book = {id: null, title:'test', authors: []};
        $rootScope.properties = {
        		book: book,
        		modalTitle: 'title',
        		buttonText: 'text',
        		messageSuffix: 'suffix'
        };
        $scope = $rootScope.$new();
        controller = $controller('BookModalAddBookController', {
        	$scope: $scope,
        	$modalInstance: modalInstance
        });
    }));

    it('saveBook is defined', inject(function () {
        // then
        expect($scope.saveBook).toBeDefined();
    }));

    it('addAuthor is defined', inject(function () {
    	// then
    	expect($scope.addAuthor).toBeDefined();
    }));

    it('removeAuthor is defined', inject(function () {
    	// then
    	expect($scope.removeAuthor).toBeDefined();
    }));
    
    it('authorsNotEmpty is defined', inject(function () {
    	// then
    	expect($scope.removeAuthor).toBeDefined();
    }));
    
    it('scope fields are initialized properly', inject(function() {
    	// then
    	expect($scope.modalTitle).toBe($scope.$parent.properties.modalTitle);
    	expect($scope.buttonText).toBe($scope.$parent.properties.buttonText);
    	
    	expect($scope.bookId).toBe($scope.$parent.properties.book.id);
    	expect($scope.bookTitle).toBe($scope.$parent.properties.book.title);
    	expect($scope.authors).toBe($scope.$parent.properties.book.authors);
    }));
    
    it('saveBook should close modal with result like book object', inject(function () {
    	// when
    	$scope.saveBook();
        // then
        expect(modalInstance.close).toHaveBeenCalled();
        expect(modalInstance.close.calls.mostRecent().args[0].id).toBe(book.id);
        expect(modalInstance.close.calls.mostRecent().args[0].title).toBe(book.title);
        expect(modalInstance.close.calls.mostRecent().args[0].authors).toBe(book.authors);
    }));

    it('addAuthor should add new author to $scope.authors', inject(function($q, $modal, Flash){
    	// given
    	$scope.authors = [];
    	var author = {id: null, firstName: 'author', lastName: '1'};
    	var modalDeferred = $q.defer();
    	spyOn($modal, 'open').and.returnValue({result: modalDeferred.promise});
    	spyOn(Flash, 'dismiss');
    	// when
    	$scope.addAuthor();
    	modalDeferred.resolve({data: author});
    	$scope.$digest();
    	// then
    	expect($modal.open).toHaveBeenCalled();
    	expect(Flash.dismiss).toHaveBeenCalled();
    	expect($scope.authors.length).toBe(1);
    }));

    it('removeAuthor should remove author from $scope.authors at index position', inject(function(){
    	// given
    	$scope.authors = [{id: null, firstName: 'author', lastName: '1'}];
    	var index = 0;
    	// when
    	$scope.removeAuthor(index);
    	// then
    	expect($scope.authors.length).toBe(0);
    }));
    
    it('authorsNotEmpty should return true', inject(function() {
    	// given
    	$scope.authors = [{id: null, firstName: 'author', lastName: '1'}];
    	// when
    	var result = $scope.authorsNotEmpty();
    	// then
    	expect(result).toBeTruthy();
    }));

    it('authorsNotEmpty should create flash when $scope.authors is empty', inject(function(Flash) {
    	// given
    	$scope.authors = [];
    	// when
    	spyOn(Flash,'create');
    	var result = $scope.authorsNotEmpty();
    	// then
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Lista autorów jest pusta!', 'custom-class');
    	expect(result).not.toBeTruthy();
    }));
//    it('search should call bookService.search', inject(function ($controller, $q, bookService) {
//    	// given
//    	$controller('BookSearchController', {$scope: $scope});
//    	$scope.bookTitle = 'a';
//    	$scope.books = [{id: 1, title: 'test'}, {id: 2, title: 'abc'}, {id: 3, title: 'xyz'}];
//    	var response = { data: [{id: 2, title: 'abc'}]};
//        var searchDeferred = $q.defer();
//        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
//        // when
//        $scope.search();
//        searchDeferred.resolve(response);
//        $scope.$digest();
//    	// then
//        expect(bookService.search).toHaveBeenCalledWith($scope.bookTitle);
//        expect($scope.books.length).toBe(1);
//    }));
//
//    it('search should call bookService.search an fail', inject(function ($controller, $q, bookService, Flash) {
//    	// given
//    	$controller('BookSearchController', {$scope: $scope});
//    	$scope.bookTitle = 'a';
//    	$scope.books = [{id: 1, title: 'test'}, {id: 2, title: 'abc'}, {id: 3, title: 'xyz'}];
//    	var searchDeferred = $q.defer();
//    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
//    	spyOn(Flash, 'create');
//    	// when
//    	$scope.search();
//    	searchDeferred.reject();
//    	$scope.$digest();
//    	// then
//    	expect(bookService.search).toHaveBeenCalledWith($scope.bookTitle);
//    	expect($scope.books.length).toBe(3);
//    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
//    }));
//
//    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
//        // given
//        $controller('BookSearchController', {$scope: $scope});
//
//        var bookId = 1;
//        $scope.books = [{id: bookId, title: 'test'}];
//        var deleteDeferred = $q.defer();
//        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
//        spyOn(Flash, 'create');
//        // when
//        $scope.deleteBook(bookId);
//        deleteDeferred.resolve();
//        $scope.$digest();
//        // then
//        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
//        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "test" została usunięta.', 'custom-class');
//        expect($scope.books.length).toBe(0);
//    }));
//
//    
//    it('adding and updating book is defined', inject(function ($controller) {
//    	// when
//    	$controller('BookSearchController', {$scope: $scope});
//    	// then
//    	expect($scope.addOrUpdateBook).toBeDefined();
//    }));
//
//    describe('addOrUpdateBook', function() {
//    	var saveDeferred;
//    	var modalDeferred;
//    	var book;
//    	beforeEach(inject(function ($controller, $modal, $q, bookService, Flash) {
//    		$scope.books = [];
//    		$controller('BookSearchController', {$scope: $scope, $modal: $modal});
//    		book = {id: null, title: 'test', authors: [{id: null, firstName: 'author', lastName: '1'}]};
//    		saveDeferred = $q.defer();
//    		modalDeferred = $q.defer();
//    		spyOn($modal, 'open').and.returnValue({result: modalDeferred.promise});
//    		spyOn(bookService, 'saveBook').and.returnValue(saveDeferred.promise);
//    		spyOn(Flash, 'create');
//    	}));
//    	
//    	it('should call bookService.saveBook and add book to books', inject(function ($controller, $modal, $q, bookService, Flash) {
//    		// given
//    		var newBook = {id: 1, title: 'test', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
//    		// when
//    		$scope.addOrUpdateBook();
//    		
//    		modalDeferred.resolve(book);
//    		saveDeferred.resolve({data: newBook});
//    		$scope.$digest();
//    		// then
//    		expect(bookService.saveBook).toHaveBeenCalledWith(book);
//    		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "test" została dodana.', 'custom-class');
//    		expect($scope.books.length).toBe(1);
//    		expect($scope.editing).toBe(false);
//    	}));
//
//    	it('should call bookService.saveBook and fail', inject(function ($controller, $modal, $q, bookService, Flash) {
//    		var newBook = {id: 1, title: 'test', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
//    		// when
//    		$scope.addOrUpdateBook();
//    		
//    		modalDeferred.resolve(newBook);
//    		saveDeferred.reject();
//    		$scope.$digest();
//    		// then
//    		expect(Flash.create).toHaveBeenCalledWith('danger', 'Błąd operacji na książce.', 'custom-class');
//    		expect($scope.books.length).toBe(0);
//    		expect($scope.editing).toBe(false);
//    	}));
//    	
//    	it('should call bookService.saveBook and update book in books', inject(function ($controller, $modal, $q, bookService, Flash) {
//    		// given
//    		$scope.books.push(book);
//    		var newBook = {id: 1, title: 'changed', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
//    		// when
//    		$scope.addOrUpdateBook(0);
//    		modalDeferred.resolve(book);
//    		saveDeferred.resolve({data: newBook});
//    		$scope.$digest();
//    		// then
//    		expect(bookService.saveBook).toHaveBeenCalledWith(book);
//    		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "changed" została zaktualizowana.', 'custom-class');
//    		expect(1).toBe($scope.books.length);
//    		expect(false).toBe($scope.editing);
//    	}));
//    	
//    	it('should dismiss modal', inject(function () {
//    		// when
//    		$scope.addOrUpdateBook();
//    		
//    		modalDeferred.reject();
//    		$scope.$digest();
//    		// then
//    		expect(0).toBe($scope.books.length);
//    		expect(false).toBe($scope.editing);
//    	}));
//    });
});
