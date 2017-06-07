var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
    function ($scope, $routeParams, passwordResetService) {

        $scope.submitPassword = function () {
            $scope.submitted = true;
            $scope.message = null;
            $scope.error = null;
            if (validatePassword()) {
                passwordResetService.save({token: $routeParams.token}, $scope.password, function () {
                    $scope.message = "Password successfully changed!";
                }, function () {
                    $scope.submitted = false;
                    $scope.error = "Error changing password";
                });
            }
        };

        var validatePassword = function () {
            if ($scope.password !== $scope.confirmPassword) {
                $scope.error = "Passwords do not match";
                $scope.submitted = false;
                return false;
            } else if ($scope.password === null || $scope.password === undefined || $scope.password.length < 5) {
                $scope.error = "Password is too short";
                $scope.submitted = false;
                return false;
            } else if ($scope.password.length >= 15) {
                $scope.error = "Password is too long";
                $scope.submitted = false;
                return false;
            } else {
                return true;
            }
        };
    });

PasswordResetController.controller('ChangePassword', function ($rootScope, $scope, $uibModalInstance, passwordResetService) {
    $scope.closeFpModal = function () {
        $uibModalInstance.dismiss();
    };

    $scope.submitEmail = function () {
        $scope.submitted = true;
        $scope.message = null;
        $scope.error = null;
        passwordResetService.get({email: $scope.email}, function () {
            $scope.message = "Message with password recovery link has been sent to your email!";
        }, function (error) {
            $scope.submitted = false;
            if (error.status === 404) {
                $scope.error = "User with such email was not found!";
            } else {
                $scope.error = "Error sending email";
            }
        });
    };
});