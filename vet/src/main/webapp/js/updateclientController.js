	/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('updateclientController', function($scope, $http, $location, $routeParams) {

		$scope.updateClient = function() {
			$http.put("/vet/webapi/clients/", $scope.client)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
				}, function(response) {
					$scope.updateStatus = 'error trying to update client';
					console.log('error http PUT clients: ' + response.status);
				});
		}
		
		$scope.getClientsById = function() {
			$http.get("/vet/webapi/clients/" + $routeParams.clientId )
			.then(function(response) {
					$scope.client = response.data[0];
					
				}, function(response) {
					console.log('error http GET clients by Id: ' + response.status);
				});
		}
		
		$scope.goToAdminSearch = function(){
			$location.path('/adminsearch/');
		}
		
		$scope.getClientsById();
	
		
	})
})()