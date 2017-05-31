angular.module('greatStartApp')
    .controller('MainController',
        ['$location', 'LoginService', '$scope', '$uibModal', '$http', '$rootScope', function ($location, LoginService, $scope, $uibModal, $http, $rootScope) {
            var modalPopup = function () {
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    size: 'sm',
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

            LoginService.authenticate();
        }]);