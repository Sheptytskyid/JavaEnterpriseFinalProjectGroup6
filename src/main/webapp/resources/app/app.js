var maimApp = angular.module('GreatStartApp', ['ngRoute']);
maimApp.config(function ($routeProvider) {
    $routeProvider
        .when('/',
            {
                controller: 'MainController',
                temlateUrl: '/app/views/Main.html'
            })
        .when('/user/:userID',
            {
                controller: 'MainController',
                temlateUrl: '/app/views/UserPage.html'
            })
        .otherwise({redirectTo: '/customers'});

});