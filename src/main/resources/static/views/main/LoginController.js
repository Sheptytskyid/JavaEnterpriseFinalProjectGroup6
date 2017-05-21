var loginController = angular.module('greatStartApp')
    .controller('LoginController',
        function (loginService, $rootScope, $scope) {

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            loginService.authenticate();

            $scope.credentials = {};
            $scope.login = function () {
                loginService.authenticate($scope.credentials, function () {
                    if ($rootScope.authenticated) {
                        //if login success
                        $scope.error = false;
                        $scope.close();
                    } else {
                        $scope.error = true;
                    }
                })
            };

        });