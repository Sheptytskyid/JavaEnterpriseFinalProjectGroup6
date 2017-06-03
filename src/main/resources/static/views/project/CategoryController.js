var CategoryController = angular.module('greatStartApp')
    .controller('CategoryController', function ($scope, Category) {
        var allCategories = function() {
            return Category.query();
        };
        $scope.categories = allCategories();
    });