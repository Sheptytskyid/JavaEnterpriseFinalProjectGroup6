var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($scope, $routeParams, Project) {
        $scope.getProject = function () {
            var project = Project.get({id: $routeParams.id}, function () {
                console.log($scope.id);
                $scope.project = project;
            }, function (error) {
                console.log(error)
            });
        };

        var investedAmount = function (project) {
            console.log(project);
            var sum = 0;
            // for (var i = 0; i < $scope.project.dtoInvestments.length; i++) {
            //     sum += project.dtoInvestments[i].sum;
            // }
            return sum;
        };

        $scope.investedAmount = investedAmount();

        $scope.getAllProjects = function () {
            var projects = Project.query({}, function () {
                $scope.projects = projects;
            });
        };

        // $scope.ivnProgressWithWidth = function () {
        //     return 'width: ' + investedAmount() * 100 / project.desc.cost;
        // };
    });