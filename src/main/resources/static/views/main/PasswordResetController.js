var PasswordResetController = angular.module('greatStartApp').controller('PasswordResetController',
        function (userService, $rootScope, $scope, passwordResetService) {
            var self = this;
            $scope.close = function () {
                $scope.forgotPassModal.dismiss();
            };

            self.submit = function() {
                passwordResetService.sendLink(self.user);
            };
        });