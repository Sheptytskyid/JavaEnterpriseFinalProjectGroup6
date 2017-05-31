angular.module('greatStartApp')
    .controller('RegisterController', function ($rootScope, $scope, User) {
        $scope.createUser = function (user) {
            User.save(user);
        };

        $scope.submit = function () {
            console.log('Saving New User', $scope.user);
            $scope.createUser($scope.user);
        };
        $scope.user = {};


    });
