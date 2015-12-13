
"use strict"

describe('AntodoProtractorSuite 1', function() {

  it('Antodo should have title', function() {
    browser.get('http://localhost:8080/antodo');
    expect(browser.getTitle()).toEqual('Antodo Application');
  });
});

