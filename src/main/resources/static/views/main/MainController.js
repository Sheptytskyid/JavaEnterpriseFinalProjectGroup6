var greatStart = angular.module('greatStartApp')
    .controller('MainController',
        ['$location', 'LoginService', '$scope', '$uibModal', '$http', '$rootScope', function ($location, LoginService, $scope, $uibModal, $http, $rootScope) {
            var modalPopup = function () {
                return $rootScope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    size: 'md',
                    backdrop: 'static',
                    scope: $rootScope
                });
            };

            $rootScope.openPopup = function () {
                modalPopup().result
                    .then(function () {
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

            $scope.forgotPass = function () {
                $scope.modalInstance.dismiss();
                $scope.forgotPassModal = $uibModal.open({
                    templateUrl: 'views/main/ForgotPassword.html',
                    controller: 'PasswordResetController',
                    size: 'sm',
                    backdrop: 'static',
                    scope: $scope
                });
            };

            LoginService.authenticate();
        }]);

greatStart.controller('LoginController', function (LoginService, $rootScope, $scope) {
    $scope.close = function () {
        $scope.modalInstance.close();
    };

    $scope.credentials = {};
    $scope.login = function () {
        LoginService.authenticate($scope.credentials, function () {
            if ($rootScope.authenticated) {
                //if login success
                // $scope.error = false;
                $scope.close();
            } else {
                $scope.error = true;
            }
        })
    };
});