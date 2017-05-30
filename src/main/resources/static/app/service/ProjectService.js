angular.module('greatStartApp')
    .factory('Project', ['$resource', function ($resource) {
        return $resource('/api/project/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
    }]);
