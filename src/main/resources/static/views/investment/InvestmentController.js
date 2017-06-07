angular.module('greatStartApp')
    .controller('InvestmentController', function ($scope, $rootScope, $location, Investment) {

        $scope.getAllInvestments = function () {
            var investments = Investment.query({}, function () {
                $scope.investments = investments;
            });
        };

        $scope.verifyInvestment = function (investment) {
            investment.verified = true;
            $scope.updateInvestment(investment);
        };

        $scope.updateInvestment = function (investment) {
            investment.investor.photo = null;
            investment.project.image = null;
            Investment.update({id: investment.id}, investment,
                function () {
                    $scope.investments.forEach(function (item) {
                        if (item.id === investment.id) {
                            item.verified = investment.verified;
                            item.paid = investment.paid;
                        }
                    });
                }
            );
        };

        $scope.payInvestment = function (investment) {
            investment.paid = true;
            $scope.updateInvestment(investment);
        };

        $scope.deleteInvestment = function (id) {
            Investment.delete({id: id}, function () {
                if ($scope.investments !== null) {
                    $scope.investments = $scope.investments.filter(function (el) {
                        return el.id !== id;
                    });
                }
            });
        };
    });