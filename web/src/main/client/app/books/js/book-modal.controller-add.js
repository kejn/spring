angular.module('app.books').controller('BookModalAddController', function ($scope, $modal, bookService, bookTitle, books, Flash) {
    'use strict';
    
    $scope.bookTitle = bookTitle;
    $scope.books = books;
    $scope.firstName = '';
    $scope.lastName = '';
    
    var insertAddedBook = function (book) {
    	$scope.books.push(book);
    };
    
    // TODO : konwersja na JSONa nie ze Stringa tylko z obiektu.
    $scope.saveBook = function () {
    	var text = '{"title":"' + $scope.bookTitle + '", "authors":[{"id":null, "firstName":"' + $scope.firstName + '", "lastName":"' + $scope.lastName + '"}]}';
    	console.log(text);
    	var bookJSON = JSON.parse(text);
    	var book = bookService.saveBook(bookJSON);
    	book.then(function (bookTo) {
    		angular.copy(bookTo.data, book);
    		insertAddedBook(book);
    		Flash.create('success', 'Książka "' + book.title + '" została dodana.', 'custom-class');
    	});
    };
    
    $scope.addAuthor = function () {
        $modal.open({
            templateUrl: 'books/html/book-modal-add-author.html',
            controller: 'BookModalAddAuthorController',
            size: 'sm'
        });
    };
});

// TODO : przenieść do osobnego pliku
angular.module('app.books').controller('BookModalAddAuthorController', function ($scope) {
	'use strict';
	
	$scope.firstName = '';
	$scope.lastName = '';
	
	// TODO : ma zwracać obiekt typu AuthorTo
	$scope.add = function () {
		$modal.open({
			templateUrl: 'books/html/book-modal-add-author.html',
			controller: 'BookModalAddAuthorController',
			size: 'sm'
		});
	};
});
