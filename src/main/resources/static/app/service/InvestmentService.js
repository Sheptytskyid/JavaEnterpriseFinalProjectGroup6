angular.module('greatStartApp')
    .factory('Investment', ['$resource', function ($resource) {
        return $resource('/api/investment/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
    }]);