angular.module('greatStartApp')
    .controller('InvestmentController', function ($scope, $rootScope, $routeParams, $location, Investment, LoginService) {

        $scope.getAllInvestments = function () {
            var investments = Investment.query({}, function () {
                $scope.investments = investments;
            });
        };

        $scope.createInvestment = function () {
            var user = angular.copy($rootScope.currentUser);
            var currentProject = angular.copy($scope.project);
            $scope.investment.investor = user;
            $scope.investment.investor.photo = null;
            $scope.investment.project = currentProject;
            $scope.investment.project.image = null;
            $scope.investment.verified = false;
            $scope.investment.paid = false;
            $scope.investment.dateOfInvestment = null;
            Investment.save($scope.investment, function () {
                LoginService.authenticate();
                // $scope.investment.dateOfInvestment = Date.now();
                console.log($scope.projectInvestments);
                $scope.projectInvestments.push($scope.investment);
                $location.path('/project/' + $scope.project.id);
            }, function (error) {
                $scope.error = true;
            });
            $scope.closeInvestmentModal();
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