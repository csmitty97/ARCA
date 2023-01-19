/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('petformController', function($scope, $http, $routeParams, $location) {

		$scope.genders = ['Male', 'Female'];
		$scope.fixedStatuses = ['Neutered', 'Spayed', 'Neither'];

		$scope.createPet = function() {
			$http.post("/vet/webapi/clients/pets", $scope.client)
				.then(function(response) {
					$scope.petId = response.data.petId;
					$scope.createStatus = 'create successful';
					console.log('create successful');
					$scope.disableCreate = true;
				}, function(response) {
					$scope.createStatus = 'error trying to create pet';
					console.log('error http POST pets: ' + response.status);
				});
		}
		

		$scope.clear = function() {
			$scope.pet.petName = '';
			$scope.pet.petAge = '';
			$scope.pet.gender = '';
			$scope.pet.fixedStatus = '';
			$scope.pet.breed = '';
			$scope.disableCreate = false;
			$scope.createForm.$setUntouched();
			$scope.createForm.$setPristine();
			$scope.createStatus = '';
		}
		
		$scope.getClientsById = function() {
			$http.get("/vet/webapi/clients/" + $routeParams.clientId )
			.then(function(response) {
					var clients = response.data;
					if (clients.length == 1) {
						$scope.client = clients[0];
					} else {
						//TODO error message
					}
				}, function(response) {
					console.log('error http GET clients by id: ' + response.status);
				});
		}
		
		
		
		//$scope.getPetsById = function(clientId) {
		//	$http.get("/vet/webapi/clients/petId/" + clientId )
		//	.then(function(response) {
		//			$scope.pets = response.data;
					
		//		}, function(response) {
		//			console.log('error http GET pets by petId: ' + response.status);
		//		});
		//}


		$scope.goToNewAppointmentForm = function(petId){
			$location.path('/appointmentform/' + petId);
		}
		
		$scope.goToAccount = function(){
			$location.path('/account/');
		}
		
		
		$scope.getClientsById();


	})
})()
