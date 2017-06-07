angular.module('greatStartApp')
    .factory('Investment', ['$resource', function ($resource) {
        return $resource('/api/investment/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            },
            my: {
                method: 'GET',
                url: '/api/investment/my',
                isArray: true
            },
            project: {
                method: 'GET',
                url: '/api/investment/project/:id',
                isArray: true
            }
        });
    }]);