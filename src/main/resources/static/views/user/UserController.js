var UserController = angular.module('greatStartApp')
    .controller('UserController', function ($scope, $rootScope, User, $location, $window) {

        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';

        $scope.user = angular.copy($rootScope.currentUser);
        $rootScope.$watch('currentUser', function () {
            $scope.user = angular.copy($rootScope.currentUser);
        });
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

        $scope.update = function (user) {
            if ($scope.myCroppedImage !== null) {
                console.log(myCroppedImage);
                user.photo = $scope.myCroppedImage.replace(/^data:image\/[a-z]+;base64,/, "");
            }
            User.update({id: user.id}, user, $scope.myCroppedImage, function () {
                User.get({id: user.id});
            })
        };

        $scope.submit = function () {
            if ($scope.user.id === null) {
                console.log('Saving New User', $scope.user);
                $scope.createUser();
            } else {
                $scope.update($scope.user);
                $location.path('/user/:id', $scope.user.id);
                $window.location.reload();
            }
        };

        $scope.getUser = function () {
            User.get({id: $scope.user.id});
        }

        //TODO write create method for user


    });