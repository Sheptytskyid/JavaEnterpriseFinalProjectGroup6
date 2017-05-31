angular.module('greatStartApp')
    .controller('UserController', function ($scope, $rootScope, User, $location) {

        $scope.flag = false;
        $scope.myImage = '';
        $scope.myCroppedImage = '';
        $scope.isImageChange = false;

        //TODO: this method retrieve currentUser. Need to think how to rewrite it in right way
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
                    $scope.isImageChange = true;
                });
            };

            reader.readAsDataURL(file);

        };

        angular.element(document.querySelector('#fileInput')).on('change', handleFileSelect);

        $scope.update = function (user) {
            return $scope.user = User.update({id: user.id}, user, function () {
                $rootScope.currentUser = User.get({id: user.id})
            })
        };

        $scope.submit = function () {
            if ($scope.isImageChange) {
                $scope.user.photo = $scope.myCroppedImage.replace(/^data:image\/[a-z]+;base64,/, "");
            }
            $scope.update($scope.user).$promise.then(function (success) {
                $location.path('/user/:id', $scope.user.id);
            }, function (error) {
                $scope.error = true;
            });

        };

        $scope.getUser = function () {
            User.get({id: $scope.user.id});
        }

    });