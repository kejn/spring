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
});
