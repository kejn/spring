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
    	// dodać "Are you sure?" OK Cancel
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
            	book: function () {
                	return {id: null, title: $scope.bookTitle, authors: []};
            	},
    			modalTitle : function () {
    				return 'Dodaj nową książkę';
    			},
    			buttonText : function () {
    				return 'Dodaj książkę';
    			}
            }
        });
        modalInstance.result.then(function (bookJSON) {
        	bookService.saveBook(bookJSON).then(function (bookTo) {
        		$scope.books.push(bookTo.data);
        		Flash.create('success', 'Książka "' + bookTo.data.title + '" została dodana.', 'custom-class');
        	}, function() {
        		Flash.create('danger', 'Nie udało się dodać książki.', 'custom-class');
        	});
        });
    };

    $scope.updateBook = function (index) {
    	var modalInstance = $modal.open({
    		templateUrl: 'books/html/book-modal-add-book.html',
    		controller: 'BookModalAddBookController',
    		size: 'lg',
    		resolve: {
    			book: function () {
    				return angular.copy($scope.books[index]);
    			},
    			modalTitle : function () {
    				return 'Edytuj książkę';
    			},
    			buttonText : function () {
    				return 'Zapisz zmiany';
    			}
    		}
    	});
    	modalInstance.result.then(function (bookJSON) {
    		bookService.saveBook(bookJSON).then(function (bookTo) {
    			angular.copy(bookTo.data, $scope.books[index]);
    			Flash.create('success', 'Książka "' + bookTo.data.title + '" została zaktualizowana.', 'custom-class');
    		}, function() {
    			Flash.create('danger', 'Nie udało się zaktualizować książki.', 'custom-class');
    		});
    	});
    };

});
