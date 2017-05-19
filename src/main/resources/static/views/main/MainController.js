var MainController = angular.module('greatStartApp')
    .controller('MainController',
        ['$location', '$scope', '$uibModal', '$http', '$rootScope', function ($location, $scope, $uibModal, $http, $rootScope) {
            var modalPopup = function () {
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    controller: 'LoginController',
                    controllerAs: 'ctrl',
                    size: 'sm',
                    backdrop: true,
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
        }])
    .controller('LoginController',
        function ($rootScope, $scope, $http, $location) {
            $scope.close = function () {
                $scope.modalInstance.close();
            };

            var authenticate = function (credentials, callback) {

                var headers = credentials ? {
                    authorization: "Basic " + btoa(credentials.email + ":" + credentials.password)
                } : {};
                $http.get('user', {headers: headers}).then(function (response) {
                    if (response.data.name) {
                        $rootScope.authenticated = true;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });

            };
            authenticate();

            $scope.credentials = {};
            $scope.login = function () {
                authenticate($scope.credentials, function () {
                    if ($rootScope.authenticated) {
                        $location.path("/");
                        $scope.error = false;
                        $scope.close();
                    } else {
                        $location.path("/");
                        $scope.error = true;
                    }
                })
            };

        });