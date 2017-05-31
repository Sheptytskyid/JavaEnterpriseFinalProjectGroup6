angular.module('greatStartApp')
    .controller('InvestmentController', function ($scope, Investment) {

        $scope.getAllInvestments = function () {
            var investments = Investment.query({}, function () {
                $scope.investments = investments;
            });
        };

        $scope.deleteInvestment = function (id) {
            Investment.delete({id: id});
            location.reload();
        };

        $scope.verifyInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update verified investment field
        };

        $scope.payInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update paid investment field
        };

        $scope.closeInvestmentModal = function () {
            console.log($scope.investmentModal);
            $scope.investmentModal.dismiss();
        };

    });