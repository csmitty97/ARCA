package com.aca.vet.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.aca.vet.model.Client;
import com.aca.vet.service.VetService;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
public class VetController {

	private VetService service = new VetService();
	
	@GET
	public List<Client> getClients() {
		return service.getClients();
	}
	
	@GET
	@Path("/{clientIdValue}")
	public List<Client> getClientsById(@PathParam("clientIdValue") Integer clientId) {
		return service.getClientsById(clientId);
	}
	
	@GET
	@Path("/phonenumber/{clientPhoneNumberValue}")
	public List<Client> getClientsByPhoneNumber(@PathParam("clientPhoneNumberValue") String phoneNumber) {
		return service.getClientsByPhoneNumber(phoneNumber);
	}
	
	@GET
	@Path("/lastname/{clientNameValue}")
	public List<Client> getClientsByLastName(@PathParam("clientNameValue") String lastName) {
		return service.getClientsByLastName(lastName);
	}
	
	@GET
	@Path("/petname/")
	public List<Client> getPetsByName(
			@QueryParam("petName") String petName,
			@QueryParam("lastName") String lastName){
		return service.getPetsByName(petName, lastName);
	}
	
	@GET
	@Path("/petId/{petIdValue}")
	public List<Client> getPetsByClientId(@PathParam("petIdValue") Integer clientId) {
		return service.getPetsByClientId(clientId);
	}
	
	@GET
	@Path("/breed/{breedValue}")
	public List<Client> getPetsByBreed(@PathParam("breedValue") String breed) {
		return service.getPetsByBreed(breed);
	}
	
	@GET
	@Path("/pets")
	public List<Client> getPets() {
		return service.getPets();
	}
	
	@GET
	@Path("/petsbypetid/{petIdValue}")
	public List<Client> getPetsByPetId(@PathParam("petIdValue") Integer petId) {
		return service.getPetsByPetId(petId);
	}
	
	@GET
	@Path("/appointments")
	public List<Client> getAppointments() {
		return service.getAppointments();
	}
	
	@GET
	@Path("/appointments/{appointmentIdValue}")
	public List<Client> getAppointmentsById(@PathParam("appointmentIdValue") Integer appointmentId) {
		return service.getAppointmentsById(appointmentId);
	}
	
	@GET
	@Path("/appointmentsbyname/")
	public List<Client> getAppointmentsByName(
			@QueryParam("petName") String petName,
			@QueryParam("lastName") String lastName){
		return service.getAppointmentsByName(petName, lastName);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Client createClient(Client newClient) {
		return service.createClient(newClient);
	}
	
	@POST
	@Path("/pets")
	@Consumes(MediaType.APPLICATION_JSON)
	public Client createPet(Client newPet) {
		return service.createPet(newPet);
	}
	
	@POST
	@Path("/appointments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Client createAppointment(Client newAppointment) {

		String value = newAppointment.getAppointmentValue().substring(0,23);
		
		newAppointment.setAppointmentDate(LocalDateTime.parse(value));
		
		return service.createAppointment(newAppointment);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Client updateClient(Client updateClient) {
		return service.updateClient(updateClient);
	}
	
	@PUT
	@Path("/pets")
	@Consumes(MediaType.APPLICATION_JSON)
	public Client updatePets(Client updatePets) {
		return service.updatePets(updatePets);
	}
	
	@PUT
	@Path("/appointments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Client updateAppointment(Client updateAppointment) {
		return service.updateAppointment(updateAppointment);
	}
	
	@DELETE
	@Path("/appointments/{appointmentIdValue}")
	public Client deleteMovieById(@PathParam("appointmentIdValue") Integer appointmentId) {
		return service.deleteAppointmentById(appointmentId);
	}
	
	@GET
	@Path("/appointmentsbypetId/{petIdValue}")
	public List<Client> getAppointmentsByPetId(@PathParam("petIdValue") Integer petId) {
		return service.getAppointmentsByPetId(petId);
	}
	
	

	
}