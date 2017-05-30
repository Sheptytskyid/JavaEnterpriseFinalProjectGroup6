var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($scope, $routeParams, Project) {
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
    });