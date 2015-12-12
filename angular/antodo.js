
"use strict";

var Antodo = angular.module('Antodo', ['ngAnimate']);

var LogError = function (data) {
	console.log('Antodo Error: ' + data);
};

class Scrap {
	constructor(description, position) {
		this.description = description;
		this.position = position;
		this.uid = uuid.v4();
	}
}


Antodo.factory('$scrapsdb', ['$window', function ($window) {
	const StorageScrapKey = 'Scraps';
	var storage = $window.localStorage;

	let service = {};
	service.store = (data) => {
		storage.setItem(StorageScrapKey, angular.toJson(data));
	};
	service.fetch = () => {
		return JSON.parse(storage.getItem(StorageScrapKey) || JSON.stringify([]));
	};
	service.listeners = [];
	service.subscribe = (listener) => {
		listener = { listener: listener, uid: uuid.v4() };
		service.listeners.push(listener);
		return listener.uid;
	};
	service.unsubscribe = (uid) => {
		let index = service.listeners.findIndex(element => element.uid.valueOf() == uid.valueOf());
		if (index < 0) return;
		service.listeners.splice(index, 1);
	};
	let xtabsync = (event) => {
		if (event.key != StorageScrapKey) return;
		service.listeners.forEach(x => x.listener());
	};
	$window.addEventListener('storage', xtabsync, false);
	return service;
}]);


Antodo.controller("OfflineController", function ($scope, $scrapsdb) {
	$scope.hello = "Hello world";
	$scope.scraps = $scrapsdb.fetch();
	$scope.incoming = '';
	$scope.submit = () => {
		if ($scope.incoming.length < 1) return;
		let rank = 0;
		$scope.scraps.forEach(element => rank = element.position > rank ? element.position : rank);
		$scope.scraps.push(new Scrap($scope.incoming, rank + 1));
		$scope.incoming = '';
		$scrapsdb.store($scope.scraps);
	};
	$scope.check = (scrap) => {
		let index = $scope.scraps.findIndex(element => element.uid.valueOf() == scrap.uid.valueOf());
		if (index < 0) return;
		$scope.scraps.splice(index, 1);
		$scrapsdb.store($scope.scraps);
	};
	$scope.reset = () => {
		$scope.scraps = [];
		$scrapsdb.store($scope.scraps);
	};
	$scrapsdb.subscribe(() => $scope.$apply(() => $scope.scraps = $scrapsdb.fetch()));
});

