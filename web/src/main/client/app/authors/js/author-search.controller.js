angular.module('app.authors').controller('AuthorSearchController', function ($scope, $window, $location, $modal, authorService, Flash) {
    'use strict';

    $scope.authors = [];
    $scope.gridOptions = { data: 'authors' };
    $scope.phrase = '';
    
    $scope.search = function () {
    	authorService.search().then(function (response) {
            angular.copy(response.data, $scope.authors);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

});
