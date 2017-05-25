
///<reference path="angular.js"/>
var InvestmentController = angular
    .module('greatStartApp')
    .controller('InvestmentController',function ($scope, $http) {
        $http.get("http://localhost:8081/api/investment/json")
            .then(function (response) {
                $scope.investments = response.data;
            });
    });