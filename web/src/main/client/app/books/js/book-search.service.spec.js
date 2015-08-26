describe('book service', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });
    beforeEach(inject());

    it('search is defined', inject(function (bookService) {
        // then
        expect(bookService.search).toBeDefined();
    }));

});
