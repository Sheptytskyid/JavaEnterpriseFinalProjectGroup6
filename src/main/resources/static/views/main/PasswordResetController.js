var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
    function ($rootScope, $scope, passwordResetService) {

        $scope.close = function () {
            $scope.forgotPassModal.dismiss();
        };

        $scope.submit = function () {
            var data = passwordResetService.query({email:$scope.email}, function(){
                console.log(data[0]);

            }, function(error) {
                $scope.error = error;
            });
        };
    });
