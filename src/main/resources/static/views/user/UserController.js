var UserController = angular.module('greatStartApp')
    .controller('UserController', ['$scope', 'userService', '$http', function ($scope, userService) {

        $scope.updateUser = updateUser;
        // $scope.getCurrentUser = getCurrentUser;
        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';

        function updateUser(user, id) {
            userService.updateUser(user, id)
                .then(function (success) {
                    $scope.user_form.$setPristine();
                }, function (error) {

                });
        }

        // function getCurrentUser() {
        //     userService.getUser(id).then(
        //         function (user) {
        //             return user;
        //         }
        //     )
        // }

        var handleFileSelect = function (evt) {
            var file = evt.currentTarget.files[0];
            var reader = new FileReader();
            reader.onload = function (evt) {
                $scope.$apply(function ($scope) {
                    $scope.myImage = evt.target.result;
                });
            };
            reader.readAsDataURL(file);
        };
        angular.element(document.querySelector('#fileInput')).on('change', handleFileSelect);
    }
    ])
;