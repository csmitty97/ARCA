package com.aca.vet.service;

import java.util.List;

import com.aca.vet.dao.VetDao;
import com.aca.vet.dao.VetDaoImpl;
import com.aca.vet.model.Client;

public class VetService {

	private VetDao vetDao = new VetDaoImpl();
	
	public List<Client> getClients() {
		return vetDao.getClients();
	}

	public List<Client> getClientsById(Integer clientId) {
		return vetDao.getClientsById(clientId);
	}

	public List<Client> getClientsByPhoneNumber(String phoneNumber) {
		return vetDao.getClientsByPhoneNumber(phoneNumber);
	}

	public List<Client> getClientsByLastName(String lastName) {
		return vetDao.getClientsByLastName(lastName);
	}

	public List<Client> getPetsByName(String petName, String lastName) {
		return vetDao.getPetsByName(petName, lastName);
	}

	public List<Client> getPetsByClientId(Integer clientId) {
		return vetDao.getPetsByClientId(clientId);
	}

	public List<Client> getPetsByBreed(String breed) {
		return vetDao.getPetsByBreed(breed);
	}

	public List<Client> getPets() {
		return vetDao.getPets();
	}

	public List<Client> getAppointments() {
		return vetDao.getAppointments();
	}

	public List<Client> getAppointmentsById(Integer appointmentId) {
		return vetDao.getAppointmentsById(appointmentId);
	}

	public List<Client> getAppointmentsByName(String petName, String lastName) {
		return vetDao.getAppointmentsByName(petName, lastName);
	}

	public Client createClient(Client newClient) {
		return vetDao.createClient(newClient);
	}

	public Client createPet(Client newPet) {
		return vetDao.createPet(newPet);
	}

	public Client createAppointment(Client newAppointment) {
		return vetDao.createAppointment(newAppointment);
	}

	public Client updateClient(Client updateClient) {
		return vetDao.updateClient(updateClient);
	}

	public Client updatePets(Client updatePets) {
		return vetDao.updatePets(updatePets);
	}

	public List<Client> getPetsByPetId(Integer petId) {
		return vetDao.getPetsByPetId(petId);
	}

	public Client updateAppointment(Client updateAppointment) {
		return vetDao.updateAppointment(updateAppointment);
	}

	public Client deleteAppointmentById(Integer appointmentId) {
		return vetDao.deleteAppointmentById(appointmentId);
	}

	public List<Client> getAppointmentsByPetId(Integer petId) {
		return vetDao.getAppointmentsByPetId(petId);
	}
	
	

}
