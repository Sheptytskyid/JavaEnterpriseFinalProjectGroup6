angular.module('greatStartApp')
    .factory('userService', function ($http) {
        var factory = {
            getUser: getUser,
            updateUser: updateUser

        };
        return factory;

        function getUser(id) {
            var user =
                $http.get('http://localhost:8080/api/user/' + id)
                    .then(function (success) {
                        user = success.data;
                    });
            return user;
        }

        function updateUser(id) {
            $http.put('http://localhost:8080/api/user/' + id)
                .then(function (success) {
                    return success;
                });
        }
    });