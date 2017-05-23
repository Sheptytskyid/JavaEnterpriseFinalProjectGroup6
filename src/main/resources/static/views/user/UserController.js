var UserController = angular.module('greatStartApp')
    .controller('UserController', function ($scope, $rootScope, User) {

        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';

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


        $scope.user = angular.copy($rootScope.currentUser);

        $scope.update = function (user) {
            User.update({id: user.id}, user, function () {
                User.get({id: user.id});
            })
        };

        $scope.submit = function () {
            if ($scope.user.id === null) {
                console.log('Saving New User', $scope.user);
                $scope.createUser();
            } else {
                console.log('Upddating user with id ', $scope.user.id);
                $scope.update($scope.user);
                console.log('User updated with id ', $scope.user.id);
            }
            $scope.reset();
        };

        $scope.reset = function () {
            $scope.user = new User();
            $scope.userForm.$setPristine();
        };

        $scope.getUser = function () {
            User.get({id: $scope.user.id});
        }

        //TODO write create method for todo


    });