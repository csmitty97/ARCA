	/**
* Access to the previously created module 'movieapp'
 */
(function() {
	let vetapp = angular.module('vetapp');

	vetapp.controller('updateappointmentController', function($scope, $http, $location, $routeParams) {

		$scope.updateAppointment = function() {
			$http.put("/vet/webapi/clients/appointments", $scope.appointment)
				.then(function(response) {
					$scope.updateStatus = 'update successful';
				}, function(response) {
					$scope.updateStatus = 'error trying to update appointment';
					console.log('error http PUT appointments: ' + response.status);
				});
		}
		
		$scope.deleteAppointment = function() {
			$http.delete("/vet/webapi/clients/appointments/" + $scope.appointment.appointmentId)
			.then(function(response) {				
				$scope.updateStatus = 'delete successful';	
				$scope.disableUpdate = true;
			}, function(response) {
				$scope.updateStatus = 'error trying to delete appointment';	
				console.log('error http DELETE appointment: ' + response.status);
			});
		}
		
		$scope.getAppointmentsById = function() {
			$http.get("/vet/webapi/clients/appointments/" + $routeParams.appointmentId )
			.then(function(response) {
					$scope.appointment = response.data[0];
					
				}, function(response) {
					console.log('error http GET appointments by Id: ' + response.status);
				});
		}
		
		$scope.goToAccount = function(){
			$location.path('/account/');
		}
		
		$scope.getAppointmentsById();
	
		
	})
})()
	