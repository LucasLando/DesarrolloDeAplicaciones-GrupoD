(function() {
  'use strict';
  /**
   * This module is used to simulate backend server for this demo application.
   */
  angular.module('content-mocksModule',['ngMockE2E'])
  
  .run(function($httpBackend) {

    var authorized = false;
    $httpBackend.whenPOST('auth/login').respond(function(method, url, data) {
      authorized = true;
      return [200];
    });
    $httpBackend.whenPOST('auth/logout').respond(function(method, url, data) {
      authorized = false;
      return [200];
    });
    
    
    $httpBackend.whenPOST('data/public').respond(function(method, url, data) {
      return [200,'Ha enviado  [' + data + '].'];
    });
    $httpBackend.whenPOST('data/protected').respond(function(method, url, data) {
      return authorized ? [200,'La empresa es [' + data + '].'] : [401];
    });

    //otherwise
    $httpBackend.whenPOST(/^\w+.*/).passThrough();
    $httpBackend.whenGET(/.*/).passThrough();

  });
})();