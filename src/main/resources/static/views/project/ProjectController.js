var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($scope, $rootScope, Project, $routeParams, $location) {

        $scope.projectImage = '';
        $scope.projectCroppedImage = '';
        $scope.imageChanged = false;

        var handleProjectImageSelect = function (evt) {
            var file = evt.currentTarget.files[0];
            var reader = new FileReader();
            reader.onload = function (evt) {
                $scope.$apply(function ($scope) {
                    $scope.projectImage = evt.target.result;
                    $scope.imageChanged = true;
                });
            };

            reader.readAsDataURL(file);

        };

        angular.element(document.querySelector('#fileInput')).on('change', handleProjectImageSelect);

        $scope.update = function (project) {
            return $scope.project = Project.update({id: project.id}, project, function () {
                $scope.project = Project.get({id: project.id})
            })
        };

        $scope.submit = function() {
            if ($scope.imageChanged) {
                $scope.project.desc.image = $scope.projectCroppedImage.replace(/^data:image\/[a-z]+;base64,/, "");
            }
            $scope.update($scope.project).$promise.then(function (success) {
                $location.path('/project/:id', $scope.project.id);
            }, function (error) {
                $scope.error = true;
            });
        };

        var allProjects = function() {
            return Project.query();
        };

        if ($routeParams.id) {
            $scope.project = Project.get({id: $routeParams.id});
        }

        $scope.projects = allProjects();

    });
