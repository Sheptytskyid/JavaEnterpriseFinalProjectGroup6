var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
    function ($rootScope, $scope, passwordResetService) {

        $scope.close = function () {
            $scope.forgotPassModal.dismiss();
        };

        $scope.submit = function () {
            $scope.message = null;
            $scope.error = null;
            var data = passwordResetService.query({email:$scope.email}, function(){
                $scope.message = data[0];
                $scope.submitted = true;
            }, function(error) {
                $scope.error = error.data.message;
            });
        };
    });
