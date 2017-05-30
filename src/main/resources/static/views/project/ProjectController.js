var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($scope, Project) {
        var allProjects = function() {
            return Project.query();
        };

        $scope.projects = allProjects();

    });