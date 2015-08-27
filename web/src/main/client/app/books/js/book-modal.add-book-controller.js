angular.module('app.books').controller('BookModalAddBookController', function ($scope, $modal, $modalInstance, Flash) {
    'use strict';
    
    Flash.dismiss();
    $scope.$parent.editing = true;
    
    $scope.modalTitle = $scope.$parent.properties.modalTitle;
    $scope.buttonText = $scope.$parent.properties.buttonText;
    
    $scope.bookTitle = $scope.$parent.properties.book.title;
    $scope.bookId = $scope.$parent.properties.book.id;
    $scope.authors = $scope.$parent.properties.book.authors;
    
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
