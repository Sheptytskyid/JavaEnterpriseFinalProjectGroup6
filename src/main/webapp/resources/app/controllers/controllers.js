
var demoApp = angular.module('greatStartApp', ['ngRoute']);
demoApp.config(function ($routeProvider) {
    $routeProvider
        .when('/',
            {
                controller: 'SimpleController',
                templateUrl: 'views/Main.html'
            })
        .when('/userPage', {
            controller: 'SimpleController',
            templateUrl: 'views/UserPage.html'
        })
        .otherwise({redirectTo: '/'});
});

demoApp.controller('SimpleController', ['$scope', function ($scope) {
    $scope.customers = [
        {name: 'Dave Jones', city: 'Phoenix'},
        {name: 'Jamie Riley', city: 'Atlanta'},
        {name: 'Heedy Wahlin', city: 'Chandler'},
        {name: 'Thomas Winter', city: 'Seattle'}
    ];
    $scope.addCustomer = function () {
        $scope.customers.push(
            {
                name: $scope.newCustomer.name,
                city: $scope.newCustomer.city
            });
    };
}]);