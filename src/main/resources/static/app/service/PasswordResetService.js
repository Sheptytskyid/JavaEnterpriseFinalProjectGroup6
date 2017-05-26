angular.module('greatStartApp')
    .factory('passwordResetService', ['$resource', function ($resource) {
        return $resource('/api/resetPassword', {email: '@email'});
    }]);