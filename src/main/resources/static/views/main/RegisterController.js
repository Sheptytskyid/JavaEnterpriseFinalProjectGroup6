angular.module('greatStartApp')
    .controller('RegisterController', function ($rootScope, $scope, User, $location) {
        $scope.createUser = function () {
            User.save($scope.user, function (success) {
                $location.path('/');
            }, function (error) {
                $scope.error = true;
            });
        };

        $scope.submit = function () {
            console.log('Saving New User', $scope.user);
            $scope.createUser($scope.user);
        };


    });
