angular.module('app.books').controller('BookModalAddController', function ($scope, bookService, bookTitle, books, Flash) {
    'use strict';
    
    $scope.bookTitle = bookTitle;
    $scope.books = books;
    $scope.firstName = '';
    $scope.lastName = '';
    
    var insertAddedBook = function (book) {
    	$scope.books.push(book);
    };
    
    $scope.saveBook = function () {
    	var text = '{"title":"' + $scope.bookTitle + '", "authors":[{"id":null, "firstName":"' + $scope.firstName + '", "lastName":"' + $scope.lastName + '"}]}';
    	console.log(text);
    	var book = JSON.parse(text);
    	bookService.saveBook(book).then(function () {
    		insertAddedBook(book);
    		Flash.create('success', 'Książka została dodana.', 'custom-class');
    	});
    };
    
    
    
});
