angular.module('greatStartApp')
    .controller('InvestmentController', function ($scope, Investment) {

        var getAllInvestments = function () {
            return Investment.query();
        };

        $scope.deleteInvestment = function (id) {
            Investment.delete({id: id});
            // callback && c
            //todo refresh page after deleting investment
        };

        $scope.verifyInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update verified investment field
            // callback && c
            //todo refresh page after deleting investment
        };

        $scope.payInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update paid investment field
            // callback && c
            //todo refresh page after deleting investment
        };

        $scope.investments = getAllInvestments();
    });