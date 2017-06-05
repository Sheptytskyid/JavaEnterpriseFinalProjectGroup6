var ProjectController = angular.module('greatStartApp')
    .controller('ProjectController', function ($rootScope, $scope, $uibModal, $routeParams, Project, $location) {
        var investedAmount = function (project) {
            var result = 0;
            for (var i = 0; i < project.dtoInvestments.length; i++) {
                result += project.dtoInvestments[i].sum;
            }
            return result;
        };

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

        var fieldsAreValid = function () {
            $scope.validationMessage = '';
            if ($scope.project.desc.minInvestment > $scope.project.desc.cost) {
                $scope.validationMessage = "Min. investment must be smaller or equal to the total cost";
                return false;
            }
            if ($scope.project.desc.minInvestment != null && $scope.project.desc.cost % $scope.project.desc.minInvestment !== 0) {
                $scope.validationMessage = "Cost must be exactly divisible by min. investment";
                return false;
            }
            return true;
        };

        $scope.submit = function() {
            if ($scope.imageChanged) {
                $scope.project.desc.image = $scope.projectCroppedImage.replace(/^data:image\/[a-z]+;base64,/, "");
            }
            if (fieldsAreValid()) {
                if (!$scope.project.id) {
                    $scope.createProject($scope.project);
                } else {
                    $scope.currentProjectId = $scope.project.id;
                    $scope.update($scope.project).$promise.then(function (success) {
                        $location.path('/project/' + $scope.currentProjectId);
                    }, function (error) {
                        $scope.error = error.status + " " + error.statusText;
                    });
                }
            } else {
                $scope.error = $scope.validationMessage;
            }
        };

        var allProjects = function() {
            return Project.query();
        };

        var myProjects = function() {
            return Project.my();
        };

        // load my projects
        if ($location.path().indexOf("/my") > -1 || $location.path().indexOf("/user/") > -1) {
            $scope.projects = myProjects();
            // load a single project
        } else if ($location.path().indexOf("/project/") > -1 && $routeParams.id) {
            var project = Project.get({id: $routeParams.id}, function () {
                $scope.project = project;
                $scope.investedAmount = investedAmount(project);
                $scope.invProgressWithWidth = ivnProgressWithWidth($scope.investedAmount, project);
            });
            // load all projects
        } else {
            $scope.project = {};
            $scope.projects = allProjects();
        }


        var ivnProgressWithWidth = function (investedAmount, project) {
            return 'width: ' + investedAmount * 100 / project.desc.cost + '%';
        };


        $scope.closeApproveModal = function () {
            $scope.projectModal.dismiss();
        };

        $scope.openApproveModal = function () {
            $scope.projectModal = $uibModal.open({
                templateUrl: 'views/investment/ApproveCreateInvestmentModal.html',
                controller: 'InvestmentController',
                size: 'md',
                backdrop: 'true',
                scope: $scope
            });
        };

        $scope.openCreateInvestmentModal = function () {
            $scope.closeApproveModal();

            $scope.investmentModal = $uibModal.open({
                templateUrl: 'views/investment/add_investment.html',
                controller: 'InvestmentController',
                size: 'sm',
                backdrop: 'true',
                scope: $scope
            });

        };

        $scope.createProject = function () {
            Project.save($scope.project, function (success) {
                $location.path('/projects');
            }, function (error) {
                $scope.error = error.status + " " + error.statusText;
            });
        };

        $scope.deleteProject = function () {
            Project.delete({id: $scope.project.id}, $scope.project, function (success) {
                $location.path('/projects');
            }, function (error) {
                $scope.error = error.status + " " + error.statusText;
            });
        };


    });