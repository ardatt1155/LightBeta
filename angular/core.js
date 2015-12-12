
var Antodo = angular.module('Antodo', []);

var LogError = function (data) {
	console.log('Antodo Error: ' + data);
};

Antodo.controller("OnlineController", function ($scope, $http) {
	$scope.form = {};

	$http.get('/api/todos').success((data) => {
		$scope.todos = data;
		console.log(data);
	}).error(LogError);

	$scope.create = function () {
		$http.put('/api/todos/put', $scope.form).success((data) => {
			$scope.form = {};
			$scope.todos = data;
		}).error(LogError);
	};

	$scope.delete = function () {
		$http.delete('/api/todos/' + uid).success((data) => {
			$scope.todos = data;
		}).error(LogError);
	};
});


Antodo.controller("OfflineController", function ($scope) {
	$scope.hello = "Hello world";
});

console.log("Sourced core.js");
