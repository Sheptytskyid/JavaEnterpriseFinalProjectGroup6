var mainApp = angular.module('greatStartApp', ['ngRoute']);
mainApp.config(function ($routeProvider) {
    $routeProvider
        .when('/',
            {
                controller: 'MainController',
                templateUrl: 'views/main/Main.html'
            })
        .when('/about',
            {
                controller: 'MainController',
                templateUrl: 'views/main/About.html'
            })
        .when('/help',
            {
                controller: 'MainController',
                templateUrl: 'views/main/Help.html'
            })
        .when('/user', {
            controller: 'UserController',
            templateUrl: 'views/user/UserPage.html'
        })
        .when('/editUser', {
            controller: 'UserController',
            templateUrl: 'views/user/EditUser.html'
        })
        .when('/projects', {
            controller: 'ProjectController',
            templateUrl: 'views/project/Projects.html'
        })
        .when('/project', {
            controller: 'ProjectController',
            templateUrl: 'views/project/ProjectPage.html'
        })
        .when('/events', {
            controller: 'EventController',
            templateUrl: 'views/event/Events.html'
        })
        .when('/event', {
            controller: 'EventController',
            templateUrl: 'views/event/EventPage.html'
        })
        .when('/interests', {
            controller: 'InterestController',
            templateUrl: 'views/interest/Interests.html'
        })
        .when('/modal-login',{
            controller: 'MainController',
            templateUrl: 'views/main/LoginPage.html'
        })
        .otherwise({redirectTo: '/'});
});

mainApp.controller('UserController', ['$scope', function ($scope) {
    $scope.flag = false;
}]);

mainApp.controller('MainController', ['$scope', function ($scope) {
}]);

mainApp.controller('ProjectController', ['$scope', function ($scope) {

}]);

mainApp.controller('EventController', ['$scope', function ($scope) {

}]);

mainApp.controller('InterestController', ['$scope', function ($scope) {

}]);
