var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
        function (userService, $rootScope, $scope, $http, $location) {
            $scope.close = function () {
                $scope.modalInstance.close();
            };
        });