var mainApp = angular.module('greatStartApp',
    ['ngRoute', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngImgCrop']);

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
                templateUrl: 'views/main/Help.html',
                resolve: {
                    checkIfUserLogon: checkIfUserHasSession
                }
            })
        .when('/user', {
            controller: 'UserController',
            templateUrl: 'views/user/UserPage.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/editUser', {
            controller: 'UserController',
            templateUrl: 'views/user/EditUser.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
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
        .when('/under_construction', {
            templateUrl: 'views/other/UnderConstruction.html'

        })
        .otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    function checkIfUserHasSession($location, $rootScope) {
        if ($rootScope.authenticated===false) {
            $location.path('/');
        }
    }

});