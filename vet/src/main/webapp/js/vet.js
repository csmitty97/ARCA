/** 
* Create a new module named 'movieapp'
*/

(function() {
	let vetapp = angular.module('vetapp', ['ngRoute']);
	
	vetapp.config(function($routeProvider) {
		  $routeProvider
		  .when("/adminsearch", {
		    templateUrl : "adminsearch.html",
		    controller : "adminsearchController"
		  })
		.when("/clientform/:clientId", {
			templateUrl : "clientform.html",
			controller : "clientformController"
		})
		.when("/newpetform/:clientId",{
			templateUrl : "newpetform.html",
			controller : "petformController"
		})
		.when("/appointmentform/:petId",{
			templateUrl : "newappointmentform.html",
			controller : "appointmentformController"
		})
		.when("/updateappointment/:appointmentId",{
			templateUrl : "updateappointment.html",
			controller : "updateappointmentController"
		})
		.when("/updateclient/:clientId",{
			templateUrl : "updateclient.html",
			controller : "updateclientController"
		})
		.when("/updatepet/:petId",{
			templateUrl : "updatepet.html",
			controller : "updatepetController"
		})
		.when("/account", {
			templateUrl : "account.html",
			controller : "accountinfoController"
		})
		  .when("/stack", {
		    templateUrl : "stack.html"
		  })
		  .when("/resume", {
		    templateUrl : "resume.html"
		  })
		  .when("/main", {
			templateUrl : "main.html",
			controller : "mainController"
		})
		  .otherwise({
			  templateUrl: "main.html",
			  controller : "mainController"
		  });
		});

})()