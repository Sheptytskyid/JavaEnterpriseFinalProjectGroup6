var MainController = angular.module('greatStartApp')
    .controller('MainController',
        ['$location', 'LoginService', '$scope', '$uibModal', '$http', '$rootScope', function ($location, LoginService, $scope, $uibModal, $http, $rootScope) {
            $scope.openPopup = function () {
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    size: 'sm',
                    backdrop: 'static',
                    scope: $scope
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