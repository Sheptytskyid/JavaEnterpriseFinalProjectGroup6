angular.module('greatStartApp')
    .controller('RegisterController', function ($rootScope, $scope, User, $location) {
        $scope.createUser = function () {
            User.save($scope.user, function (success) {
                $location.path('/');
            }, function (error) {
                if (error.status === 409) {
                    var email = $scope.user.email.toUpperCase();
                    $scope.error = 'User with '+email+' already exist';
                }
                if(error.status===400) {
                    $scope.error='Please correct your credentials. Email should look like "username@example.com" or amend your password'
                }
            });
        };

        $scope.submit = function () {
            console.log('Saving New User', $scope.user);
            $scope.createUser($scope.user);
        };

    });
