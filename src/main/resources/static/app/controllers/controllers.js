var mainApp = angular.module('greatStartApp', ['ngRoute', 'ngResource', 'ngAnimate', 'ngSanitize', 'ui.bootstrap', 'ngImgCrop']);

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
        .when('/projects', {
            controller: 'ProjectController',
            templateUrl: 'views/project/Projects.html'
        })
        .when('/project/new', {
            controller: 'ProjectController',
            templateUrl: 'views/project/EditProject.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/projects/my', {
            controller: 'ProjectController',
            templateUrl: 'views/project/Projects.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/project/:id', {
            controller: 'ProjectController',
            templateUrl: 'views/project/ProjectPage.html'
            /*resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }*/
        })
        .when('/project/:id/edit', {
            controller: 'ProjectController',
            templateUrl: 'views/project/EditProject.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
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
        .when('/investment', {
            controller: 'InvestmentController',
            templateUrl: 'views/investment/Investments.html'
        })
        .when('/under_construction', {
            templateUrl: 'views/other/UnderConstruction.html'

        })
        .when('/createAccount', {
            templateUrl: 'views/main/CreateAccount.html',
            controller: 'RegisterController'
        })
        .when('/user/changePassword/', {
            controller: 'PasswordResetController',
            templateUrl: 'views/user/ChangePassword.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/user/:id', {
            controller: 'UserController',
            templateUrl: 'views/user/UserPage.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/user/:id/edit', {
            controller: 'UserController',
            templateUrl: 'views/user/EditUser.html',
            resolve: {
                checkIfUserLogon: checkIfUserHasSession
            }
        })
        .when('/admin', {
            controller: 'AdminPanelController',
            templateUrl: 'views/admin/AdminPanelPage.html'
        })
        .otherwise({redirectTo: '/'});

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    function checkIfUserHasSession($location, $rootScope) {
        if ($rootScope.authenticated===false) {
            $location.path('/');
        }
    }

});