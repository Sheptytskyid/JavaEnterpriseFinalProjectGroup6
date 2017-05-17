var MainController = angular.module('greatStartApp')
    .controller('MainController',
        ['$scope', '$uibModal', '$location', '$rootScope', function ($scope, $uibModal) {
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
        }]);

var LoginController = angular.module('greatStartApp')
    .controller('LoginController',
        function ($rootScope, $scope, $location, $http) {

            $scope.close = function () {
                $scope.modalInstance.close();
            };

            var self = this;

            var authenticate = function (credentials, callback) {

                var headers = credentials ? {
                        authorization: "Basic " + btoa(credentials.username + ":" + credentials.password)
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

            self.credentials = {};
            self.login = function () {
                authenticate(self.credentials, function () {
                    if ($rootScope.authenticated) {
                        $location.path("/events");
                        self.error = false;
                    } else {
                        $location.path("/projects");
                        self.error = true;
                    }
                })
            }

        });