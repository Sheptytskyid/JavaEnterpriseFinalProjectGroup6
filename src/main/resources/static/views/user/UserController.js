var UserController = angular.module('greatStartApp')
    .controller('UserController', ['$scope', 'userService', '$http', function ($scope, $http, userService) {

        $scope.user = {};
        $http.get('http://localgost:8080/api/user/1').then(function (success) {
            $scope.user = success.data;
        });

        // $scope.submit = ngSubmit;
        // $scope.updateUser = updateUser;
        // $scope.getUser = getUser;
        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';

        $scope.user = {};
        $http.get('http://localgost:8080/api/user/1').then(function (success) {
            $scope.user = success.data;
        });

        // function updateUser(user, id) {
        //     userService.updateUser(user, id)
        //         .then(function (success) {
        //             $scope.user_form.$setPristine();
        //         }, function (error) {
        //
        //         });
        // }
        //
        // function getUser(id) {
        //     userService.getUser(id).then(
        //         function (user) {
        //             $scope.user = user;
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