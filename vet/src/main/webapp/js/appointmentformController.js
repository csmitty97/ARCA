/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('appointmentformController', function($scope, $http, $location, $routeParams) {

	//	$scope.pet.appointmentValue = $scope.pet.appointmentDate;
		
		
		//$scope.pet.appointmentDate = null; 

		$scope.createAppointment = function() {
			console.log($scope.pet.appointmentDate);
			$http.post("/vet/webapi/clients/appointments", $scope.pet)
				.then(function(response) {
					$scope.createStatus = 'create successful';
					console.log('Create Successful');
					$scope.disableCreate = true;
				}, function(response) {
					$scope.createStatus = 'error trying to create appointment';
					console.log('error http POST appointments: ' + response.status);
				});
		}
		
			$scope.getPetsByPetId = function() {
			$http.get("/vet/webapi/clients/petsbypetid/" + $routeParams.petId )
			.then(function(response) {
					var pets = response.data;
					if (pets.length == 1) {
						$scope.pet = pets[0];
					} else {
						//TODO error message
					}
				}, function(response) {
					console.log('error http GET pets by petId: ' + response.status);
				});
		}
		
		
		
			$scope.getAppointmentsById = function() {
			$http.get("/vet/webapi/clients/appointments/" + $routeParams.appointmentId )
			.then(function(response) {
					var appointments = response.data;
					if (appointments.length == 1) {
						$scope.appointment = appointments[0];
					} else {
						//TODO error message
					}
				}, function(response) {
					console.log('error http GET appointments by id: ' + response.status);
				});
		}
		
		
		
		$scope.goToMain = function() {
			$location.path('/main/');

		}

		$scope.getPetsByPetId();


	})
})()