var MainController = angular.module('greatStartApp')
    .controller('MainController',
        ['$location', 'userService', '$scope', '$uibModal', '$http', '$rootScope', function ($location, userService, $scope, $uibModal, $http, $rootScope) {
            var modalPopup = function () {
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    size: 'md',
                    backdrop: 'static',
                    scope: $scope
                });
            };

            $scope.openPopup = function () {
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

            userService.authenticate();
        }]);