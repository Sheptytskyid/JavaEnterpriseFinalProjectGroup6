var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($rootScope, $scope, $uibModal, $routeParams, Project) {
        var investedAmount = function (project) {
            var result = 0;
            for (var i = 0; i < project.dtoInvestments.length; i++) {
                if (project.dtoInvestments[i].paid) {
                    result += project.dtoInvestments[i].sum;
                }
            }
            return result;
        };

        var ivnProgressWithWidth = function (investedAmount, project) {
            return 'width: ' + investedAmount * 100 / project.desc.cost + '%';
        };

        $scope.getProject = function () {
            var project = Project.get({id: $routeParams.id}, function () {
                $scope.project = project;
                $scope.investedAmount = investedAmount(project);
                $scope.invProgressWithWidth = ivnProgressWithWidth($scope.investedAmount, project);
            });
        };

        $scope.getAllProjects = function () {
            var projects = Project.query({}, function () {
                $scope.projects = projects;
            });
        };

        $scope.closeApproveModal = function () {
            $scope.projectModal.dismiss();
        };

        $scope.openApproveModal = function () {
            $scope.projectModal = $uibModal.open({
                templateUrl: 'views/project/ApproveCreateInvestmentModal.html',
                controller: 'ProjectController',
                size: 'md',
                backdrop: 'true',
                scope: $scope
            });
        };

        $scope.openCreateInvestmentModal = function () {
            $scope.projectModal.close();

            $scope.investmentModal = $uibModal.open({
                templateUrl: 'views/investment/add_investment.html',
                controller: 'InvestmentController',
                size: 'md',
                backdrop: 'true',
                scope: $scope
            });

        };
    });