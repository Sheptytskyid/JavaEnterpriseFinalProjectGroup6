var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($scope, Project, $routeParams) {

        $scope.myImage = '';
        $scope.myCroppedImage = '';
        $scope.isImageChange = false;

        var handleFileSelect = function (evt) {
            var file = evt.currentTarget.files[0];
            var reader = new FileReader();
            reader.onload = function (evt) {
                $scope.$apply(function ($scope) {
                    $scope.myImage = evt.target.result;
                    $scope.isImageChange = true;
                });
            };

            reader.readAsDataURL(file);

        };

        angular.element(document.querySelector('#fileInput')).on('change', handleFileSelect);

        var allProjects = function() {
            return Project.query();
        };

        $scope.project = Project.get({id: $routeParams.id});

        $scope.projects = allProjects();

    });
