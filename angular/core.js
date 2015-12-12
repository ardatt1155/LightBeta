
"use strict";

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


class Scrap {
	constructor(description, position) {
		this.description = description;
		this.position = position;
		this.uid = uuid.v4();
	}
}

var storage = window.localStorage;


Antodo.controller("OfflineController", function ($scope) {
	$scope.hello = "Hello world";
	$scope.scraps = JSON.parse(storage.getItem('Scraps') || JSON.stringify([]));
	$scope.incoming = '';
	$scope.submit = () => {
		if ($scope.incoming.length < 1) return;
		$scope.scraps.push(new Scrap($scope.incoming, $scope.scraps.length));
		$scope.incoming = '';
		storage.setItem('Scraps', JSON.stringify($scope.scraps));
	};
	$scope.check = (scrap) => {
		let index = $scope.scraps.findIndex(element => element.uid.valueOf() == scrap.uid.valueOf());
		if (index < 0) return;
		$scope.scraps.splice(index, 1);
		storage.setItem('Scraps', JSON.stringify($scope.scraps));
	};
	$scope.reset = () => {
		$scope.scraps = [];
		storage.setItem('Scraps', JSON.stringify($scope.scraps));
	};
});

console.log("Sourced core.js");
