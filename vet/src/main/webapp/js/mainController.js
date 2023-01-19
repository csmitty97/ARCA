/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('mainController', function($scope, $http, $location) {
		
		$scope.getLastClient = function() {
			$http.get("/vet/webapi/clients")
				.then(function(response) {
					$scope.clients = response.data;
					$scope.lastClient = $scope.clients.length - 1;
					console.log('last client: ' + $scope.lastClient);
				}, function(response) {
					console.log('error http GET clients: ' + response.status);
				});

		}
		
		$scope.goToClientForm = function() {
			$http.get("/vet/webapi/clients")
				.then(function(response) {
					$scope.clients = response.data;
					$scope.lastClient = $scope.clients.length + 1;
					console.log('last client: ' + $scope.lastClient);
					$location.path('/clientform/' + $scope.lastClient);
					
				}, function(response) {
					console.log('error http GET clients: ' + response.status);
				});
				
			
			
		}
		
		
		
		$scope.goToAccount = function(){
			$location.path('/account/');
		}
		
	
		
	})
})()