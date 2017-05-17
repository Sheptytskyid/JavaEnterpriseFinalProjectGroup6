var mainApp = angular.module('greatStartApp', ['ngRoute', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngImgCrop']);

mainApp.config(function ($routeProvider, $httpProvider) {
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
        .when('/createAccount', {
            controller: 'MainController',
            templateUrl: 'views/main/CreateAccount.html'
        })
        .otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
});