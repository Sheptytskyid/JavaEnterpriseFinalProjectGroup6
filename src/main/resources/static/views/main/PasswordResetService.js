angular.module('greatStartApp').service('passwordResetService', function ($http) {
    var service = this;

    service.sendLink = function(user) {
        console.log('Form is submitted with following user', user);
        return $http.post('/user/resetPassword', user);
    }
});