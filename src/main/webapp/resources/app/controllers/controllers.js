var mainApp = angular.module('greatStartApp', ['ngRoute']);
mainApp.config(function ($routeProvider) {
    $routeProvider
        .when('/',
            {
                controller: 'MainController',
                templateUrl: 'views/Main.html'
            })
        .when('/user', {
            controller: 'UserController',
            templateUrl: 'views/user/UserPage.html'
        })
        .when('/projects', {
            controller: 'ProjectController',
            templateUrl: 'views/project/Projects.html'
        })
        .when('/events', {
            controller: 'EventController',
            templateUrl: 'views/event/Events.html'
        })
        .when('/interests', {
            controller: 'InterestController',
            templateUrl: 'views/interest/Interests.html'
        })
        .otherwise({redirectTo: '/'});
});

mainApp.controller('UserController', ['$scope', function ($scope) {
    $scope.favourites = false;
}]);

mainApp.controller('MainController', ['$scope', function ($scope) {
}]);

mainApp.controller('ProjectController', ['$scope', function ($scope) {

}]);

mainApp.controller('EventController', ['$scope', function ($scope) {

}]);

mainApp.controller('InterestController', ['$scope', function ($scope) {

}]);