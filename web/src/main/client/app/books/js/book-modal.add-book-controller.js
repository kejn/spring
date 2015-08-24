angular.module('app.books').controller('BookModalAddBookController', function ($scope, $modal, $modalInstance, book, modalTitle, buttonText) {
    'use strict';
    
    $scope.modalTitle = modalTitle;
    $scope.buttonText = buttonText;
    
    $scope.bookTitle = book.title;
    $scope.bookId = book.id;
    $scope.authors = book.authors;
    
    function BookTo(id, title, authors) {
    	this.id = id;
    	this.title = title;
    	this.authors = authors;
    }
    
    $scope.saveBook = function () {
    	var bookJSON = JSON.stringify(new BookTo($scope.bookId, $scope.bookTitle, $scope.authors));
    	$modalInstance.close(bookJSON);
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
