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
});
