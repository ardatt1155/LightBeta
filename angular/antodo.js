
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

const StorageScrapKey = 'Scraps';
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
	$scope.scraps = JSON.parse(storage.getItem(StorageScrapKey) || JSON.stringify([]));
	$scope.incoming = '';
	$scope.submit = () => {
		if ($scope.incoming.length < 1) return;
		let rank = 0;
		$scope.scraps.forEach(element => rank = element.position > rank ? element.position : rank);
		$scope.scraps.push(new Scrap($scope.incoming, rank + 1));
		$scope.incoming = '';
		storage.setItem(StorageScrapKey, JSON.stringify($scope.scraps));
	};
	$scope.check = (scrap) => {
		let index = $scope.scraps.findIndex(element => element.uid.valueOf() == scrap.uid.valueOf());
		if (index < 0) return;
		$scope.scraps.splice(index, 1);
		storage.setItem(StorageScrapKey, JSON.stringify($scope.scraps));
	};
	$scope.reset = () => {
		$scope.scraps = [];
		storage.setItem(StorageScrapKey, JSON.stringify($scope.scraps));
	};
	let xtabsync = (event) => {
		if (event.key != StorageScrapKey) return;
		$scope.$apply(() => $scope.scraps = JSON.parse(storage.getItem(StorageScrapKey) || JSON.stringify([])));
	};
	window.addEventListener('storage', xtabsync, false);
});

console.log("Sourced core.js");
