angular.module('greatStartApp')
    .factory('Category', ['$resource', function ($resource) {
        return $resource('/api/category/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
    }]);
