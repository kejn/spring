describe('book-modal add-author-controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    var controller;
    var modalInstance;
    var author;
    beforeEach(inject(function ($rootScope, $controller) {
    	$scope = $rootScope.$new();
    	modalInstance = {
    			close: jasmine.createSpy('modalInstance.close'),
    	};
        controller = $controller('BookModalAddAuthorController', {
        	$scope: $scope,
        	$modalInstance: modalInstance
        });
        author = {id: null, firstName:'test', lastName: '1'};
    }));

    it('addAuthor is defined', inject(function () {
    	// then
    	expect($scope.addAuthor).toBeDefined();
    }));
    
    it('should close modal and pass new author as result', inject(function() {
    	// given
    	$scope.firstName = author.firstName;
    	$scope.lastName = author.lastName;
    	// when
    	$scope.addAuthor();
    	// then
    	expect(modalInstance.close).toHaveBeenCalled();
    	expect(modalInstance.close.calls.mostRecent().args[0].id).toBe(author.id);
    	expect(modalInstance.close.calls.mostRecent().args[0].firstName).toBe(author.firstName);
    	expect(modalInstance.close.calls.mostRecent().args[0].lastName).toBe(author.lastName);
    }));
});
