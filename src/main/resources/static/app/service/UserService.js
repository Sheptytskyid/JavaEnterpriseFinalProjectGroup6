angular.module('greatStartApp')
    .factory('userService', function ($http, $q) {
        var factory = {
            getUser: getUser,
            updateUser: updateUser

        };
        return factory;

        function getUser(id) {
            var def = $q.defer();
            $http.get('http://localgost:8080/api/user/' + id)
                .then(function (success) {
                    def.resolve(success.data);
                }, function (error) {
                    def.reject(error)
                });
            return def;
        }

        function updateUser(id) {
            var def = $q.defer();
            $http.put('http://localgost:8080/api/user/' + id)
                .then(function (success) {
                    def.resolve(success.data);
                }, function (error) {
                    def.reject(error);
                });
            return def.promise;
        }
    });