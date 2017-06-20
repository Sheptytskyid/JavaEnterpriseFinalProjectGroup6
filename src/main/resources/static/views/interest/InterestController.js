angular.module('greatStartApp')
    .controller('InterestController', function ($scope, InvInterest, $location, User, $rootScope) {
        $scope.relocateContact = function (interest) {
            $rootScope.investorContact = interest;
            $location.path('/interest/contact');
        };

        $scope.getContact = function () {
            $scope.investorContact = $rootScope.investorContact;
        };

        $scope.getEditPage = function (interest) {
            $rootScope.interestToEditPage = interest;
            $location.path('/interest/edit');
        };

        $scope.interestForEdit = function () {
            $scope.interest = $rootScope.interestToEditPage;
        };

        var fieldsAreValid = function () {
            $scope.validationMessage = '';
            if ($scope.interest.amountInvestment.length > 8) {
                $scope.validationMessage = "Not more than 8 numbers!";
                return false;
            }
            return true;
        };

        $scope.submit = function () {
            if (fieldsAreValid()) {
                if (!$scope.interest.id) {
                    $scope.createInterest($scope.interest);
                } else {
                    $scope.currentInterestId = $scope.interest.id;
                    $scope.update($scope.interest).$promise.then(function (success) {
                        $location.path('/interest/my');
                    }, function (error) {
                        $scope.error = error.status + " " + error.statusText;
                    });
                }
            } else {
                $scope.error = $scope.validationMessage;
            }
        };

        $scope.createInterest = function () {
            InvInterest.save($scope.interest, function (success) {
                $location.path('/interest/my');
            }, function (error) {
                $scope.error = error.status + " " + error.statusText;
            });
        };

        $scope.update = function (interest) {
            return $scope.interest = InvInterest.update({id: interest.id}, interest, function () {
                $scope.interest = InvInterest.get({id: interest.id})
            })
        };

        $scope.deleteInterest = function (id) {
            InvInterest.delete({id: id}, function (success) {
                $location.path('/interest/my');
            }, function (error) {
                $scope.error = error.status + " " + error.statusText;
            });
        };

        $scope.getAllInvInterest = function () {
            var interests = InvInterest.query({}, function () {
                $scope.interests = interests;
                console.log($scope.interests);
            })
        };

        $scope.getMyInterests = function () {
            var myInterests = InvInterest.my({}, function () {
                $scope.myInterests = myInterests;
            });
        }
    });