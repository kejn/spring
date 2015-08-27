describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('search should call bookService.search', inject(function ($controller, $q, bookService) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.bookTitle = 'a';
    	$scope.books = [{id: 1, title: 'test'}, {id: 2, title: 'abc'}, {id: 3, title: 'xyz'}];
    	var response = { data: [{id: 2, title: 'abc'}]};
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        // when
        $scope.search();
        searchDeferred.resolve(response);
        $scope.$digest();
    	// then
        expect(bookService.search).toHaveBeenCalledWith($scope.bookTitle);
        expect($scope.books.length).toBe(1);
    }));

    it('search should call bookService.search an fail', inject(function ($controller, $q, bookService, Flash) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.bookTitle = 'a';
    	$scope.books = [{id: 1, title: 'test'}, {id: 2, title: 'abc'}, {id: 3, title: 'xyz'}];
    	var searchDeferred = $q.defer();
    	spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.search();
    	searchDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(bookService.search).toHaveBeenCalledWith($scope.bookTitle);
    	expect($scope.books.length).toBe(3);
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    }));

    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});

        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "test" została usunięta.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));

    
    it('adding and updating book is defined', inject(function ($controller) {
    	// when
    	$controller('BookSearchController', {$scope: $scope});
    	// then
    	expect($scope.addOrUpdateBook).toBeDefined();
    }));

    describe('addOrUpdateBook', function() {
    	var saveDeferred;
    	var modalDeferred;
    	var book;
    	beforeEach(inject(function ($controller, $modal, $q, bookService, Flash) {
    		$scope.books = [];
    		$controller('BookSearchController', {$scope: $scope, $modal: $modal});
    		book = {id: null, title: 'test', authors: [{id: null, firstName: 'author', lastName: '1'}]};
    		saveDeferred = $q.defer();
    		modalDeferred = $q.defer();
    		spyOn($modal, 'open').and.returnValue({result: modalDeferred.promise});
    		spyOn(bookService, 'saveBook').and.returnValue(saveDeferred.promise);
    		spyOn(Flash, 'create');
    	}));
    	
    	it('should call bookService.saveBook and add book to books', inject(function ($controller, $modal, $q, bookService, Flash) {
    		// given
    		var newBook = {id: 1, title: 'test', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
    		// when
    		$scope.addOrUpdateBook();
    		
    		modalDeferred.resolve(book);
    		saveDeferred.resolve({data: newBook});
    		$scope.$digest();
    		// then
    		expect(bookService.saveBook).toHaveBeenCalledWith(book);
    		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "test" została dodana.', 'custom-class');
    		expect($scope.books.length).toBe(1);
    		expect($scope.editing).toBe(false);
    	}));

    	it('should call bookService.saveBook and fail', inject(function ($controller, $modal, $q, bookService, Flash) {
    		var newBook = {id: 1, title: 'test', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
    		// when
    		$scope.addOrUpdateBook();
    		
    		modalDeferred.resolve(newBook);
    		saveDeferred.reject();
    		$scope.$digest();
    		// then
    		expect(Flash.create).toHaveBeenCalledWith('danger', 'Błąd operacji na książce.', 'custom-class');
    		expect($scope.books.length).toBe(0);
    		expect($scope.editing).toBe(false);
    	}));
    	
    	it('should call bookService.saveBook and update book in books', inject(function ($controller, $modal, $q, bookService, Flash) {
    		// given
    		$scope.books.push(book);
    		var newBook = {id: 1, title: 'changed', authors: [{id: 1, firstName: 'author', lastName: '1'}]};
    		// when
    		$scope.addOrUpdateBook(0);
    		modalDeferred.resolve(book);
    		saveDeferred.resolve({data: newBook});
    		$scope.$digest();
    		// then
    		expect(bookService.saveBook).toHaveBeenCalledWith(book);
    		expect(Flash.create).toHaveBeenCalledWith('success', 'Książka "changed" została zaktualizowana.', 'custom-class');
    		expect(1).toBe($scope.books.length);
    		expect(false).toBe($scope.editing);
    	}));
    	
    	it('should dismiss modal', inject(function () {
    		// when
    		$scope.addOrUpdateBook();
    		
    		modalDeferred.reject();
    		$scope.$digest();
    		// then
    		expect(0).toBe($scope.books.length);
    		expect(false).toBe($scope.editing);
    	}));
    });
});
