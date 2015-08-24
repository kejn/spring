angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, $modal, bookService, Flash) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.bookTitle = '';

    var removeBookById = function (bookId) {
    	var title = '';
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
            	title = $scope.books[i].title;
                $scope.books.splice(i, 1);
                break;
            }
        }
        return title;
    };
    
    $scope.search = function () {
        bookService.search($scope.bookTitle).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            var bookTitle = removeBookById(bookId);
            Flash.create('success', 'Książka "' + bookTitle + '" została usunięta.', 'custom-class');
        });
    };

    $scope.addBook = function () {
        var modalInstance = $modal.open({
            templateUrl: 'books/html/book-modal-add-book.html',
            controller: 'BookModalAddBookController',
            size: 'lg',
            resolve: {
            	bookTitle: function () {
                	return $scope.bookTitle;
            	},
        		books: function() {
        			return $scope.books;
        		}
            }
        });
        modalInstance.result.then(function (bookJSON) {
        	var book = bookService.saveBook(bookJSON);
        	book.then(function (bookTo) {
        		angular.copy(bookTo.data, book);
        		$scope.books.push(book);
        		Flash.create('success', 'Książka "' + book.title + '" została dodana.', 'custom-class');
        	},
        	function() {
        		Flash.create('danger', 'Nie udało się dodać książki.', 'custom-class');
        	});
        });
    };

});
