var UserController = angular.module('greatStartApp')
    .controller('UserController', function ($scope, $rootScope, User, $location, $window) {

        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';

        $scope.$on('LOAD', function () {
            $scope.loading = true;
        });
        $scope.$on('UNLOAD', function () {
            $scope.loading = false;
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

        function dataURItoBlob(dataURI) {
            var byteString = atob(dataURI.split(',')[1]);
            var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0]
            var ab = new ArrayBuffer(byteString.length);
            var ia = new Uint8Array(ab);
            for (var i = 0; i < byteString.length; i++) {
                ia[i] = byteString.charCodeAt(i);
            }

            var blob = new Blob([ab], {type: mimeString});
            return blob;
        }


        $scope.user = angular.copy($rootScope.currentUser);
        $rootScope.$watch('currentUser', function () {
            $scope.user = angular.copy($rootScope.currentUser);
            console.log("Copy from $rootScope", $scope.user);
        });

        $scope.update = function (user) {
            user.photo = dataURItoBlob($scope.myCroppedImage);
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
                $scope.$emit('LOAD');
                // $window.location.reload();
                // $location.path('/user/:id', $scope.user.id);
                $scope.$emit('UNLOAD');
            }
        };

        $scope.getUser = function () {
            User.get({id: $scope.user.id});
        }

        //TODO write create method for todo


    });