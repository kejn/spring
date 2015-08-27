describe('author service', function () {
    'use strict';

    var $authorService;
    beforeEach(function () {
        module('app.main');
        module('app.authors');
        inject(function(authorService) {
        	$authorService = authorService;
        });
    });

    it('search is defined', inject(function () {
        // then
        expect($authorService.search).toBeDefined();
    }));

    it('calls authorRestService.search', inject(function($q, authorRestService) {
    	// given
        var searchDeferred = $q.defer();
        spyOn(authorRestService, 'search').and.returnValue(searchDeferred.promise);
        // when
        $authorService.search();
        searchDeferred.resolve({data: null});
    	// then
        expect(authorRestService.search).toHaveBeenCalled();
    }));
});
