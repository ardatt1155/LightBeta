
"use strict"

describe('AntodoProtractorSuite 1', function () {
	beforeEach(function () {
		browser.get('http://localhost:8080/antodo');		
	});

	it('Antodo should have title', function () {
		expect(browser.getTitle()).toEqual('Antodo Application');
	});

	it('Antodo should greet', function () {
		expect(element(by.binding('hello')).getText()).toBe('Hello. Manage your todo list here.');
	});

	it('Antodo should reset', function () {
		let reset = element(by.id('reset'));
		reset.click();
		let list = element.all((by.repeater('scrap in scraps')));
		expect(list.count()).toBe(0);
		expect(element(by.id('noscrap')).getText()).toBe('Watch Star Wars ... again!');
	});
});

