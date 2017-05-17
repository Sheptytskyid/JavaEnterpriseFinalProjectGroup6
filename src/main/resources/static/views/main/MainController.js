var MainController = angular.module('greatStartApp')
    .controller('MainController',
        ['$scope', '$uibModal','$location','$rootScope', function ($scope, $uibModal, $location, $rootScope, $http) {
            var modalPopup = function () {
                return $scope.modalInstance = $uibModal.open({
                    templateUrl: 'views/main/LoginPage.html',
                    // controller: 'MainController',
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

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            var authenticate = function (callback) {
                $http.get('/user').then(function (data) {
                    if (data.name) {
                        $rootScope.authenticated = true;
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }).error(function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });
            };
            authenticate();
            $scope.credentials = {};
            $scope.login = function () {
                $http.post('login', $.params($scope.credentials), {
                    headers: {
                        "content-type": "application/x-www-form-urlencoded"
                    }
                }).then(function (data) {
                    authenticate(function () {
                        if ($rootScope.authenticated) {
                            $location.path("/");
                            $scope.error = false;
                        } else {
                            $location.path("/login");
                            $scope.error = true;
                        }
                    });
                }).error(function (data) {
                    $location.path("/login");
                    $scope.error = true;
                    $rootScope.authenticated = false;
                })
            };

        }]);