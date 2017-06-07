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
            var date = new Date(Date.UTC(input[0], input[1] - 1, input[2], input[3], input[4], input[5]));
            var offset = date.getTimezoneOffset() / 60;
            var hours = date.getHours();
            date.setHours(hours + offset);

            return date;
        }
    });