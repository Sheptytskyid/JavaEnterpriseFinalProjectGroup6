angular.module('greatStartApp')
    .factory('Project', ['$resource', function ($resource) {
        return $resource('/api/project/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            },
            my: {
                method: 'GET',
                url: '/api/projects/my',
                isArray: true
            }
        });
    }]);
