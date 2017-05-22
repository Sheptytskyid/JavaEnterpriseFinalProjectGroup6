var userService = angular.module('greatStartApp')
    .factory('loginService', function ($rootScope, $http) {
        return {
            authenticate: function (credentials, callback) {

                var headers = credentials ? {
                    authorization: "Basic " + btoa(credentials.email + ":" + credentials.password)
                } : {};
                $http.get('user', {headers: headers}).then(function (response) {
                    if (response.data.name) {
                        $rootScope.authenticated = true;
                        $http.get('http://localhost:8080/api/current').then(function (success) {
                            $rootScope.currentUser = success.data;
                        })
                    } else {
                        $rootScope.authenticated = false;
                    }
                    callback && callback();
                }, function () {
                    $rootScope.authenticated = false;
                    callback && callback();
                });
            }
        }
    });