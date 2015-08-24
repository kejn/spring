angular.module('app.books').controller('BookModalAddAuthorController', function ($scope, $modalInstance) {
	'use strict';
	
	$scope.firstName = '';
	$scope.lastName = '';
	
	function AuthorTo(firstName, lastName) {
    	this.id = null;
    	this.firstName = firstName;
    	this.lastName = lastName;
    }
	
	$scope.addAuthor = function () {
		$modalInstance.close(new AuthorTo($scope.firstName, $scope.lastName));
	};
});
