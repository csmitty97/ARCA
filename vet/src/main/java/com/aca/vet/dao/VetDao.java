package com.aca.vet.dao;

import java.util.List;

import com.aca.vet.model.Client;

public interface VetDao {
	
	public List<Client> getClients();
	public List<Client> getClientsById(Integer clientId);
	public List<Client> getClientsByPhoneNumber(String phoneNumber);
	public List<Client> getClientsByLastName(String lastName);
	public List<Client> getPetsByName(String petName, String lastName);
	public List<Client> getPetsByClientId(Integer clientId);
	public List<Client> getPetsByBreed(String breed);
	public List<Client> getPets();
	public List<Client> getAppointments();
	public List<Client> getAppointmentsById(Integer appointmentId);
	public List<Client> getAppointmentsByName(String petName, String lastName);
	public Client createClient(Client newClient);
	public Client createPet(Client newPet);
	public Client createAppointment(Client newAppointment);
	public Client updateClient(Client updateClient);
	public Client updatePets(Client updatePets);
	public List<Client> getPetsByPetId(Integer petId);
	public Client updateAppointment(Client updateAppointment);
	public Client deleteAppointmentById(Integer appointmentId);
	public List<Client> getAppointmentsByPetId(Integer petId);
	
}
