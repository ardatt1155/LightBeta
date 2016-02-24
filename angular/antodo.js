
"use strict";

// -- WEBPACK --
//require("./../node_modules/angular/angular.js");
//require("./../node_modules/angular-animate/angular-animate.js");
//require("./../node_modules/node-uuid/uuid.js");

angular.module('Antodo', ['ngAnimate', 'ui.router']);

angular.module('Antodo').constant('$antodoconsts', {
	ScrapStorageKey: 'Scraps',
	ScrapCountKey:'ScrpC'
});

angular.module('Antodo').config(function ($stateProvider, $urlRouterProvider) {
	$urlRouterProvider.otherwise('#antodo');
	$stateProvider.state('#charts', {
		url: '#charts',
		template: $('#charts-partial').html()
	});	
});

angular.module('Antodo').factory('$antodointercept', function ($q) {
	const interceptor = {
		responseError: function (config) {
			var deferred = $q.defer();
			console.log(config);
			return deferred.reject(config);
		}
	};
	return interceptor;
});

angular.module('Antodo').config(function ($httpProvider) {  
    $httpProvider.interceptors.push('$antodointercept');
});

angular.module('Antodo').directive('antodoDnd', ['$window', '$scrapsdb', function ($window, $scrapsdb) {
	var directive = {
		restrict: 'A',
		scope: {
			scraps: '=',
			scrap: '='
		},
		link: function ($scope, $element, $attributes, $controller, $transclude) {
			let element = $element[0];
			element.addEventListener('drop', (event) => {
				let source = event.dataTransfer.getData('source');
				let indexS = $scope.scraps.findIndex(x => x.uid.valueOf() == source.valueOf());
				let indexT = $scope.scraps.findIndex(x => x.uid.valueOf() == $scope.scrap.uid.valueOf());
				if (indexS < 0 || indexT < 0) return;
				source = $scope.scraps[indexS];
				$scope.$apply(() => {
					$scope.scraps.splice(indexS, 1);
					$scope.scraps.splice(indexT, 0, source);
					$scrapsdb.store($scope.scraps);
				});
			});
			let canceller = (event) => {
				event.preventDefault();
			};
			element.addEventListener('dragstart', (event) => {
				event.dataTransfer.setData('source', $scope.scrap.uid);
			});
			element.addEventListener('dragenter', canceller);
			element.addEventListener('dragover', canceller);
		}
	};
	return directive;
}]);

angular.module('Antodo').factory('$scrapsdb', ['$window', '$antodoconsts', function ($window, $antodoconsts) {
	const ScrapStorageKey = $antodoconsts.ScrapStorageKey;
	const ScrapCountKey = $antodoconsts.ScrapCountKey;
	var storage = $window.localStorage;
	var listeners = [];

	let service = {};
	service.count = (count) => {
		if (Number.isInteger(count) == false) return Number.parseInt(storage.getItem(ScrapCountKey));
		else storage.setItem(ScrapCountKey, count);
	};
	service.store = (data) => {
		storage.setItem(ScrapStorageKey, angular.toJson(data));
	};
	service.fetch = () => {
		return JSON.parse(storage.getItem(ScrapStorageKey) || JSON.stringify([]));
	};
	service.subscribe = (listener) => {
		listener = { listener: listener, uid: uuid.v4() };
		listeners.push(listener);
		return listener.uid;
	};
	service.unsubscribe = (uid) => {
		let index = listeners.findIndex(element => element.uid.valueOf() == uid.valueOf());
		if (index < 0) return;
		listeners.splice(index, 1);
	};
	let xtabsync = (event) => {
		if (event.key != ScrapStorageKey) return;
		listeners.forEach(x => x.listener());
	};
	$window.addEventListener('storage', xtabsync, false);
	return service;
}]);


angular.module('Antodo').controller("OfflineController", function ($scope, $scrapsdb, $antodoconsts, $rootScope, $http) {
	let buildScrap = (description) => { return {description: description, uid: uuid.v4()}; };
	let persist = () => {
		$scrapsdb.store($scope.scraps);
		$scrapsdb.count($scope.count);
		$rootScope.$broadcast($antodoconsts.ScrapStorageKey + $antodoconsts.ScrapCountKey);
	};
	$scope.hello = "Antodo Application";
	$scope.greetings = "This is the best application to manage your task list";
	$scope.scraps = $scrapsdb.fetch();
	$scope.count = $scrapsdb.count();
	$scope.incoming = '';
	$scope.submit = () => {
		if ($scope.incoming.length < 1) return;
		$scope.scraps.push(buildScrap($scope.incoming));
		$scope.incoming = '';
		$scope.count++;
		persist();
	};
	$scope.check = (scrap) => {
		let index = $scope.scraps.findIndex(element => element.uid.valueOf() == scrap.uid.valueOf());
		if (index < 0) return;
		$scope.scraps.splice(index, 1);
		persist();
	};
	$scope.reset = () => {
		$scope.scraps = [];
		$scope.count = 0;
		persist();
	};
	$scrapsdb.subscribe(() => $scope.$apply(() => $scope.scraps = $scrapsdb.fetch()));
	$scope.quote = 'Querying quote of the day .... ';
	$http.get('http://api.theysaidso.com/qod').then((response) => {
		$scope.quote = response ? response.data.contents.quotes[0].quote : 'TheySaidSo ain\'t talking to us. :(';
	});
});

angular.module('Antodo').controller("ChartController", function ($scope, $scrapsdb, $antodoconsts) {
	let tag = (strings, ...values) => [0, 1, 2].reduce((p, c, i, a) => p + strings[c] + values[c] + "\n", '');
	$scope.pie = () => {
		const selector = 'charts';
		$scope.count = $scrapsdb.count();
		$scope.scraps = $scrapsdb.fetch();
		$scope.progress = $scope.scraps.length;
		$scope.complete = $scope.count - $scope.progress;
		(document.getElementById(selector) || {}).innerHTML = "";
		$scope.message = $scope.count < 1 ? "No data to show charts" : tag`complete = ${$scope.complete} progress = ${$scope.progress} total = ${$scope.count}`;
		if ($scope.count > 0) D3Pie('#' + selector, $scope.complete, $scope.progress);
	};
	$scope.$on($antodoconsts.ScrapStorageKey + $antodoconsts.ScrapCountKey, () => $scope.pie());
	$scope.pie();
});
