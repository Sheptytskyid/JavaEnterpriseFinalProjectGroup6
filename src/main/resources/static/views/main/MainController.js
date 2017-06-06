var greatStart = angular.module('greatStartApp')
    .controller('MainController',
        ['$location', 'LoginService', '$scope', '$uibModal', '$http', '$rootScope', function ($location, LoginService, $scope, $uibModal, $http, $rootScope) {

            $rootScope.openPopup = function () {
                $rootScope.loginModal = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    size: 'md',
                    backdrop: 'static'
                    // scope: $rootScope
                }).result
                    .then(function () {
                        $rootScope.forgotPass();
                    })
                    .then(null, function () {
                    });
            };


            $scope.logout = function () {
                $http.post('logout', {}).finally(function () {
                    $location.path("/");
                    $rootScope.authenticated = false;
                });
            };

            $rootScope.forgotPass = function () {
                $rootScope.forgotPassModal = $uibModal.open({
                    templateUrl: 'views/main/ForgotPassword.html',
                    controller: 'ChangePassword',
                    size: 'sm',
                    backdrop: 'static',
                    scope: $rootScope
                }).result.then(function () {
                }).then(function () {

                });
            };

            LoginService.authenticate();
        }]);

greatStart.controller('LoginController', function ($rootScope, $scope, $uibModalInstance, LoginService) {
    $scope.close = function () {
        $uibModalInstance.dismiss();
    };

    $scope.credentials = {};
    $scope.login = function () {
        LoginService.authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                $uibModalInstance.dismiss();
            } else {
                $scope.error = true;
            }
        })
    };

    $scope.openForgotPass = function () {
        $uibModalInstance.close();
    };
});