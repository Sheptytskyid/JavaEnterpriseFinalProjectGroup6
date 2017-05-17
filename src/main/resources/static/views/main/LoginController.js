// var LoginController = angular.module('greatStartApp')
//     .controller('LoginController',
//         function ($rootScope, $scope,$uibModal, $location) {
//             // var authenticate = function (callback) {
//             //     $http.get('user').then(function (data) {
//             //         if (data.name) {
//             //             $rootScope.authenticated = true;
//             //         } else {
//             //             $rootScope.authenticated = false;
//             //         }
//             //         callback && callback();
//             //     }).error(function () {
//             //         $rootScope.authenticated = false;
//             //         callback && callback();
//             //     });
//             // };
//             // authenticate();
//             // $scope.credentials = {};
//             // $scope.login = function () {
//             //     $http.post('login', $.params($scope.credentials), {
//             //         headers: {
//             //             "content-type": "application/x-www-form-urlencoded"
//             //         }
//             //     }).then(function (data) {
//             //         authenticate(function () {
//             //             if ($rootScope.authenticated) {
//             //                 $location.path("/");
//             //                 $scope.error = false;
//             //             } else {
//             //                 $location.path("/login");
//             //                 $scope.error = true;
//             //             }
//             //         });
//             //     }).error(function (data) {
//             //         $location.path("/login");
//             //         $scope.error = true;
//             //         $rootScope.authenticated = false;
//             //     })
//             // };
//         });