angular.module('greatStartApp')
    .factory('User', ['$resource', function ($resource) {
        return $resource('/api/user/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
    }]);

angular.module('greatStartApp')
    .filter("asDate", function () {
        return function (input) {
            return new Date(Date.UTC(input[0], input[1] - 1, input[2]));
        }
    });