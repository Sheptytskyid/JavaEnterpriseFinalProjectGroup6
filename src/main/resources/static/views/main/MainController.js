var MainController = angular.module('greatStartApp')
    .controller('MainController', ['$scope', '$uibModal', function ($scope, $uibModal) {
        var modalPopup = function () {
            return $scope.modalInstance = $uibModal.open({
                templateUrl: 'views/main/LoginPage.html',
                size: 'sm',
                backdrop: true,
                scope: $scope
            });
        };

        $scope.openPopup = function () {
            modalPopup().result
                .then(function () {
                })
                .then(null, function () {
                });
        };

        $scope.close = function () {
            $scope.modalInstance.close();
        };

    }]);