angular.module('app.books').controller('BookModalAddBookController', function ($scope, $modal, $modalInstance, properties, Flash) {
    'use strict';
    
    Flash.dismiss();
    $scope.$parent.editing = true;
    
    $scope.modalTitle = properties.modalTitle;
    $scope.buttonText = properties.buttonText;
    
    $scope.bookTitle = properties.book.title;
    $scope.bookId = properties.book.id;
    $scope.authors = properties.book.authors;
    
    $scope.saveBook = function () {
    	$modalInstance.close(new $scope.$parent.BookTo($scope.bookId, $scope.bookTitle, $scope.authors));
    };
    
    $scope.addAuthor = function () {
        var modalInstance = $modal.open({
            templateUrl: 'books/html/book-modal-add-author.html',
            controller: 'BookModalAddAuthorController',
            size: 'sm'
        });
        modalInstance.result.then(function (newAuthor) {
        	Flash.dismiss();
        	$scope.authors.push(newAuthor);
        });
    };
    
    $scope.removeAuthor = function(index) {
    	$scope.authors.splice(index,1);
    };
    
    $scope.authorsNotEmpty = function() {
    	if($scope.authors.length === 0) {
    		Flash.create('danger', 'Lista autorów jest pusta!', 'custom-class');
    		return false;
    	}
    	return true;
    };
});
