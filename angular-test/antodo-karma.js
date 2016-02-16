
"use strict";

describe("AntodoSuite 1", function() {

	beforeEach(module('Antodo'));

	it("Tests Antodo constants", inject(function ($injector) {
		const actual = $injector.get('$antodoconsts');
		expect(actual.ScrapStorageKey).toBe('Scraps');
		expect(Object.keys(actual).length).toBe(1);
  	}));
});


describe("AntodoSuite 2", function() {
	beforeEach(module('Antodo'));
	beforeEach(function () {
		module(function ($provide) {
      		$provide.constant('$antodoconsts', {
      			ScrapStorageKey: 'ScrapsTest'
      		});
	    });
	});

	it("Tests Antodo constant-mock", inject(function ($injector) {
		const actual = $injector.get('$antodoconsts');
		expect(actual.ScrapStorageKey).toBe('ScrapsTest');
		expect(Object.keys(actual).length).toBe(1);
	}));

	it("Tests Antodo scrapsdb", inject(function ($injector) {
		const scrapsdb = $injector.get('$scrapsdb');
		expect(typeof scrapsdb).toBe('object');
		expect(Object.keys(scrapsdb).length).toBe(4);
		expect(typeof scrapsdb.store).toBe('function');
		expect(typeof scrapsdb.fetch).toBe('function');
		expect(typeof scrapsdb.subscribe).toBe('function');
		expect(typeof scrapsdb.unsubscribe).toBe('function');

		let expected = { hello: 'world'};
		scrapsdb.store(expected);
		let actual = scrapsdb.fetch();
		expect(Object.keys(actual).length).toBe(Object.keys(expected).length);
		expect(actual.hello).toBe(expected.hello);

		//@todo How do you test the subscribe/unsubscribe behavior? How do you get a mock $window object?
	}));
});

