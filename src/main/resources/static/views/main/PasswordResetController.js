var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
        function (userService, $rootScope, $scope, $http, $location) {

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            $scope.sendLink = function () {
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
        });