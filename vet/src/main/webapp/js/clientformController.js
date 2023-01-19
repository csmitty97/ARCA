/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('clientformController', function($scope, $http, $location, $routeParams) {

	$scope.client = {
		clientId: $routeParams.clientId
	}

		$scope.createClient = function() {
			$http.post("/vet/webapi/clients", $scope.client)
				.then(function(response) {
					$scope.createStatus = 'create successful';
					console.log('Create Successful');
					$scope.disableCreate = true;
				}, function(response) {
					$scope.createStatus = 'error trying to create client';
					console.log('error http POST clients: ' + response.status);
				});
		}
		
		$scope.getAllClients = function() {
			$http.get("/vet/webapi/clients")
				.then(function(response) {
					$scope.clients = response.data;
					console.log('number of clients: ' + $scope.clients.length);
				}, function(response) {
					console.log('error http GET clients: ' + response.status);
				});
		}
		
	
		
		$scope.goToAccount = function() {
			$location.path('/account/');

		}
		
		$scope.goToPetForm = function(clientId){
			$location.path('/newpetform/' + clientId);
		}
		



	})
})()