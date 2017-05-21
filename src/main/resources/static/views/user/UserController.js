var UserController = angular.module('greatStartApp')
    .controller('UserController', ['$scope', function ($scope) {
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
    }]);