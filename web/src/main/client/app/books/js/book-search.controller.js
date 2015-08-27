angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, $modal, bookService, Flash) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.bookTitle = '';
    $scope.editing = false;
    
    $scope.BookTo = function (id, title, authors) {
    	this.id = id;
    	this.title = title;
    	this.authors = authors;
    };
    
    function ModalProperties(book, modalTitle, buttonText, messageSuffix) {
    	this.book = book;
		this.modalTitle = modalTitle;
		this.buttonText = buttonText;
		this.messageSuffix = messageSuffix;
    }

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

    $scope.addOrUpdateBook = function (index) {
    	var newBook = (typeof index === 'undefined');
    	
    	if (newBook) {
    		$scope.properties = new ModalProperties(new $scope.BookTo(null, $scope.bookTitle, []),
    										'Dodaj nową książkę',
    										'Dodaj książkę',
    										'dodana.');
    	} else {
    		$scope.properties = new ModalProperties(angular.copy($scope.books[index]),
    										'Edytuj książkę',
    										'Zapisz zmiany',
    										'zaktualizowana.');
    	}
    	var modalInstance = $modal.open({
    		templateUrl: 'books/html/book-modal-add-book.html',
    		controller: 'BookModalAddBookController',
    		size: 'lg',
    		scope: $scope
    	});
    	modalInstance.result.then(function (bookTo) {
    		bookService.saveBook(bookTo).then(function (book) {
    			if(newBook) {
    				$scope.books.push(book.data);
    			} else {
    				angular.copy(book.data, $scope.books[index]);
    			}
    			var message = 'Książka "' + book.data.title + '" została ' + $scope.properties.messageSuffix;
    			Flash.create('success', message, 'custom-class');
    		}, function() {
    			Flash.create('danger', 'Błąd operacji na książce.', 'custom-class');
    		});
    		$scope.editing = false;
    	}, function () {
    		Flash.dismiss();
    		$scope.editing = false;
    	});

    };

});
