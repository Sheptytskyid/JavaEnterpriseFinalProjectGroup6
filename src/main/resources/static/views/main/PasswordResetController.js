var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
    function ($rootScope, $scope, passwordResetService) {

        $scope.close = function () {
            $scope.forgotPassModal.dismiss();
        };

        $scope.submit = function () {
            passwordResetService.get({email:$scope.email}, function(){
            }, function(error) {
                $scope.error = error;
            }).$promise.then(function(data){
                console.log(data);
            });
        };
    });
