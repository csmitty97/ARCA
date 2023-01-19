/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('adminsearchController', function($scope, $http, $location, $routeParams) {
		
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
					$scope.clients = response.data;
				//	$scope.disablephoneNumber = true;
			//		if (clients.length == 1) {
					//	$scope.client = clients[0];
			
				//	} else {
				//		//TODO error message
					//}
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
		
			$scope.getClientsByLastName = function() {
			$http.get("/vet/webapi/clients/lastname/" + $scope.client.lastName )
			.then(function(response) {
					$scope.clients = response.data;
					
				}, function(response) {
					console.log('error http GET clients by lastName: ' + response.status);
				});
		}
		
			$scope.getClientsByName = function() {
			$http.get("/vet/webapi/clients/petname/" + $scope.client.lastName )
			.then(function(response) {
					$scope.clients = response.data;
					
				}, function(response) {
					console.log('error http GET clients by lastName: ' + response.status);
				});
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
		
		$scope.goToUpdateClientForm = function(clientId){
			$location.path('/updateclient/'+ clientId);
		}
		
		$scope.goToUpdatePetForm = function(petId){
			$location.path('/updatepet/' + petId);
		}
		
		
	//$scope.getClientsById();
	//$scope.getAllClients();
	//$scope.getAllPets();
	//$scope.getAllAppointments();


	})
})()