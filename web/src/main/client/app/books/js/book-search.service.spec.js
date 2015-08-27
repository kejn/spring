describe('book service', function () {
    'use strict';

    var $scope, bookService;
    beforeEach(function () {
        module('app.main');
        module('app.books');
        inject(function(bookService) {
        	bookService = bookService;
        });
    });

    it('search is defined', inject(function (bookService) {
        // then
        expect(bookService.search).toBeDefined();
    }));

});
