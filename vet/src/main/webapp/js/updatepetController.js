	/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('updatepetController', function($scope, $http, $location, $routeParams) {

		$scope.genders = ['Male', 'Female'];
		$scope.fixedStatuses = ['Neutered', 'Spayed', 'Neither'];

		$scope.updatePet = function() {
			$http.put("/vet/webapi/clients/pets", $scope.pet)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
				}, function(response) {
					$scope.updateStatus = 'error trying to update pet';
					console.log('error http PUT pets: ' + response.status);
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
		
		$scope.getPetsById = function() {
			$http.get("/vet/webapi/clients/petId/" + $routeParams.clientId )
			.then(function(response) {
					$scope.appointment = response.data[0];
					
				}, function(response) {
					console.log('error http GET appointments by Id: ' + response.status);
				});
		}
		
		$scope.goToAdminSearch = function(){
			$location.path('/adminsearch/');
		}
		
		$scope.getPetsByPetId();
	
		
	})
})()