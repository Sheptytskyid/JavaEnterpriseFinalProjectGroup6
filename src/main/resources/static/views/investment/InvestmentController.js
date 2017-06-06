angular.module('greatStartApp')
    .controller('InvestmentController', function ($scope, $rootScope, $location, Investment, LoginService) {

        $scope.getAllInvestments = function () {
            var investments = Investment.query({}, function () {
                $scope.investments = investments;
            }, function () {
                $scope.investments = null;
            });
        };

        $scope.verifyInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update verified investment field
        };

        $scope.payInvestment = function (investment) {
            // Investment.save({id: id});
            //todo update paid investment field
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