var loginController = angular.module('greatStartApp')
    .controller('LoginController',
        function (LoginService, $rootScope, $scope, $uibModal) {

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            LoginService.authenticate();

            $scope.credentials = {};
            $scope.login = function () {
                LoginService.authenticate($scope.credentials, function () {
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
                $scope.forgotPassModal = $uibModal.open({
                    templateUrl: 'views/main/ForgotPassword.html',
                    controller: 'PasswordResetController',
                    size: 'sm',
                    backdrop: 'static',
                    scope: $scope
                });
            };
        });