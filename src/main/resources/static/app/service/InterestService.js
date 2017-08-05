angular.module('greatStartApp')
    .factory('InvInterest', ['$resource', function ($resource) {
        return $resource('/api/interest/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            },
            delete: {
                method: 'DELETE'
            },
            my: {
                method: 'GET',
                url: '/api/interest/my',
                isArray: true
            }
        })
    }]);
