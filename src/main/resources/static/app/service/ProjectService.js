angular.module('greatStartApp')
    .factory('Project', ['$resource', function ($resource) {
        return $resource('/project/:id', {id: '@_id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            }
        });
    }]);