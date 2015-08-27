describe('author rest service', function () {
    'use strict';

    var $authorRestService;
    var httpBackend;
    var context;
    beforeEach(function () {
        module('app.main');
        module('app.authors');
        inject(function(authorRestService, $httpBackend, currentContextPath) {
        	$authorRestService = authorRestService;
        	httpBackend = $httpBackend;
        	context = currentContextPath.get();
        });
    });

    it('search is defined', inject(function () {
        // then
        expect($authorRestService.search).toBeDefined();
    }));

    it('calls authorRestService.search with GET', inject(function() {
        // when
    	httpBackend.expect('GET', context + 'rest/authors/all-authors').respond({data: null});
    	$authorRestService.search();
    	// then
    	httpBackend.flush();
    }));
});
