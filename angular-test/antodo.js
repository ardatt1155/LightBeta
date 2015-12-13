
"use strict";

describe("Hello testing", function() {

	beforeEach(module('Antodo'));

	it("Tests Antodo constants", inject(function($injector) {
		const actual = $injector.get('$antodoconsts');
		expect(actual.StorageScrapKey).toBe('Scraps');
		expect(Object.keys(actual).length).toBe(1);
  	}));
});


