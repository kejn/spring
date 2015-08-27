describe('author controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.authors');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('AuthorSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('search should call authorService.search', inject(function ($controller, $q, authorService) {
    	// given
    	$controller('AuthorSearchController', {$scope: $scope});
        var searchDeferred = $q.defer();
        spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
        // when
        $scope.search();
        searchDeferred.resolve({data: null});
        $scope.$digest();
    	// then
        expect(authorService.search).toHaveBeenCalled();
    }));

    it('search should call authorService.search and fail', inject(function ($controller, $q, authorService, Flash) {
    	// given
    	$controller('AuthorSearchController', {$scope: $scope});
    	var searchDeferred = $q.defer();
    	spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
    	spyOn(Flash, 'create');
    	// when
    	$scope.search();
    	searchDeferred.reject();
    	$scope.$digest();
    	// then
    	expect(authorService.search).toHaveBeenCalled();
    	expect(Flash.create).toHaveBeenCalledWith('danger', 'Wyjątek', 'custom-class');
    }));
});
