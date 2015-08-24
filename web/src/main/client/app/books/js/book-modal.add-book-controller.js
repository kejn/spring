angular.module('app.books').controller('BookModalAddBookController', function ($scope, $modal, $modalInstance, properties) {
    'use strict';
    
    $scope.modalTitle = properties.modalTitle;
    $scope.buttonText = properties.buttonText;
    
    $scope.bookTitle = properties.book.title;
    $scope.bookId = properties.book.id;
    $scope.authors = properties.book.authors;
    
    function BookTo(id, title, authors) {
    	this.id = id;
    	this.title = title;
    	this.authors = authors;
    }
    
    $scope.saveBook = function () {
    	$modalInstance.close(new BookTo($scope.bookId, $scope.bookTitle, $scope.authors));
    };
    
    $scope.addAuthor = function () {
        var modalInstance = $modal.open({
            templateUrl: 'books/html/book-modal-add-author.html',
            controller: 'BookModalAddAuthorController',
            size: 'sm'
        });
        modalInstance.result.then(function (newAuthor) {
        	$scope.authors.push(newAuthor);
        });
    };
    
    $scope.removeAuthor = function(index) {
    	$scope.authors.splice(index,1);
    };
    
    $scope.authorsAreValid = function() {
    	if($scope.authors.length === 0) {
    		return false;
    	}
    	for(var i = 0; i < $scope.authors.length; ++i) {
    		if(!!!$scope.authors[i].firstName || !!!$scope.authors[i].lastName) {
    			return false;
    		}
    	}
    	return true;
    };
});
