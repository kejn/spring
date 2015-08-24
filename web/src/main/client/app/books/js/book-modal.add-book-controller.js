angular.module('app.books').controller('BookModalAddBookController', function ($scope, $modal, $modalInstance, bookTitle) {
    'use strict';
    
    $scope.bookTitle = bookTitle;
    $scope.authors = [];
    
    function AuthorTo(firstName, lastName) {
    	this.id = null;
    	this.firstName = firstName;
    	this.lastName = lastName;
    }

    function BookTo(title, authors) {
    	this.id = null;
    	this.title = title;
    	this.authors = authors;
    }
    
    $scope.saveBook = function () {
    	var bookJSON = JSON.stringify(new BookTo($scope.bookTitle, $scope.authors));
    	console.log("bookToJSON: " + bookJSON);
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
    }
    
    $scope.authorsAreValid = function() {
    	if($scope.authors.length == 0) {
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
