/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('accountinfoController', function($scope, $http, $location, $routeParams) {
		
		$scope.getAllClients = function() {
			$http.get("/vet/webapi/clients")
				.then(function(response) {
					$scope.clients = response.data;
					console.log('number of clients: ' + $scope.clients.length);
				}, function(response) {
					console.log('error http GET clients: ' + response.status);
				});
		}
		
		$scope.getAllPets = function() {
			$http.get("/vet/webapi/clients/pets")
				.then(function(response) {
					$scope.pets = response.data;
					console.log('number of pets: ' + $scope.pets.length);
				}, function(response) {
					console.log('error http GET pets: ' + response.status);
				});
		}
		
		$scope.getAllAppointments = function() {
			$http.get("/vet/webapi/clients/appointments")
				.then(function(response) {
					$scope.appointments = response.data;
					console.log('number of appointments: ' + $scope.appointments.length);
				}, function(response) {
					console.log('error http GET appointments: ' + response.status);
				});
		}

		$scope.getClientsById = function() {
			$http.get("/vet/webapi/clients/" + $scope.client.clientId )
			.then(function(response) {
					var clients = response.data;
					if (clients.length == 1) {
						$scope.client = clients[0];
						$scope.getPetsById($scope.client.clientId);
					} else {
						//TODO error message
					}
				}, function(response) {
					console.log('error http GET clients by id: ' + response.status);
				});
		}
		
		$scope.getClientsByPhoneNumber = function() {
			$http.get("/vet/webapi/clients/phonenumber/" + $scope.client.phoneNumber )
			.then(function(response) {
					$scope.clients = response.data;
					
				}, function(response) {
					console.log('error http GET clients by phoneNumber: ' + response.status);
				});
		}
		
		$scope.updateClient = function() {
			$http.put("/vet/webapi/clients", $scope.appointment)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
				}, function(response) {
					$scope.updateStatus = 'error trying to update movie';
					console.log('error http PUT clients: ' + response.status);
				});
		}
		
		
	
		
		$scope.goToNewPetForm = function(clientId){
			$location.path('/newpetform/' + clientId);
		}
		
		$scope.goToNewAppointmentForm = function(petId){
			$location.path('/appointmentform/' + petId);
		}
		
		$scope.goToUpdateForm = function(appointmentId){
			$location.path('/updateappointment/' + appointmentId);
		}
		
			$scope.getPetsById = function(clientId) {
			$http.get("/vet/webapi/clients/petId/" + clientId )
			.then(function(response) {
					$scope.pets = response.data;
					
				}, function(response) {
					console.log('error http GET pets by petId: ' + response.status);
				});
		}
		
		$scope.getAppointmentsByPetId = function(petId) {
			$http.get("/vet/webapi/clients/appointmentsbypetId/" + petId )
			.then(function(response) {
					$scope.appointments = response.data;
					
				}, function(response) {
					console.log('error http GET appointments by petId: ' + response.status);
				});
		}
		
		
	//$scope.getClientsById();
	//$scope.getAllClients();
	//$scope.getAllPets();
	//$scope.getAllAppointments();


	})
})()