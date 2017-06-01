angular.module('greatStartApp')
    .factory('passwordResetService', ['$resource', function ($resource) {
        return $resource('/user/resetPassword', {email: '@email', token: "@token"});
    }]);