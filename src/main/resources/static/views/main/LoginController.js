var loginController = angular.module('greatStartApp').controller('LoginController',
        function (userService, $rootScope, $scope, $http, $uibModal) {

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            userService.authenticate();

            $scope.credentials = {};
            $scope.login = function () {
                userService.authenticate($scope.credentials, function () {
                    if ($rootScope.authenticated) {
                        //if login success
                        $scope.error = false;
                        $scope.close();
                    } else {
                        $scope.error = true;
                    }
                })
            };

            $scope.forgotPass = function () {
                $scope.close();
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/ForgotPassword.html',
                    controller: 'PasswordResetController',
                    size: 'sm',
                    backdrop: 'static',
                    scope: $scope
                });
            };
        });