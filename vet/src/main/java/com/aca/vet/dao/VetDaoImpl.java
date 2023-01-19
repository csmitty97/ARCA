package com.aca.vet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.aca.vet.model.Client;
import com.aca.vet.model.FixedStatus;
import com.aca.vet.model.Gender;

public class VetDaoImpl implements VetDao {

	private static String selectAllClients = "SELECT clientid, firstName, lastName, phone, address, email, updateDateTime, createDateTime "
			+ "FROM clientinfo ";
	
	private static String selectClientsById = "SELECT clientId, firstName, lastName, phone, address, email, updateDateTime, createDateTime "
			+ "FROM clientinfo "
			+ "WHERE clientId = ?";
	
	private static String selectClientsByPhoneNumber = "SELECT clientId, firstName, lastName, phone, address, email, updateDateTime, createDateTime "
			+ "FROM clientinfo "
			+ "WHERE phone = ?";
	
	private static String selectClientsByLastName = "SELECT clientId, firstName, lastName, phone, address, email, updateDateTime, createDateTime "
			+ "FROM clientinfo "
			+ "WHERE lastName = ?";
	
	private static String selectPetsByName = "SELECT newpetinfo.clientId, petId,  lastName, phone, petName, age, sex, Fix, breed "
			+ "FROM newpetinfo "
			+ "JOIN clientinfo "
			+ "ON clientinfo.clientId = newpetinfo.clientId "
			+ "WHERE petName = ? "
			+ "AND lastName = ?";
	
	private static String selectPetsByClientId = "SELECT newpetinfo.clientId, petId,  lastName, phone, petName, age, sex, Fix, breed "
			+ "FROM newpetinfo "
			+ "JOIN clientinfo "
			+ "ON clientinfo.clientId = newpetinfo.clientId "
			+ "WHERE newpetinfo.clientId = ?";
	
	private static String selectPetsByBreed = "SELECT newpetinfo.clientId, petId,  lastName, phone, petName, age, sex, Fix, breed "
			+ "FROM newpetinfo "
			+ "JOIN clientinfo "
			+ "ON clientinfo.clientId = newpetinfo.clientId "
			+ "WHERE breed = ?";
	
	private static String selectAllPets = "SELECT newpetinfo.clientId, petId,  lastName, phone, petName, age, sex, Fix, breed "
			+ "FROM newpetinfo "
			+ "JOIN clientinfo "
			+ "ON clientinfo.clientId = newpetinfo.clientId" ;

	
	private static String selectAllAppointments = "SELECT appointmentId, clientId, petId, petName, lastName, phone, visitReason, appointmentDate, createDateTime, updateDateTime "
			+ "FROM newappointmentinfo";
	
	private static String selectAppointmentsById = "SELECT appointmentId, clientId, petId, petName, lastName, phone, visitReason, appointmentDate, createDateTime, updateDateTime "
			+ "FROM newappointmentinfo "
			+ "WHERE appointmentId = ?";
	
	private static String selectAppointmentsByName = "SELECT appointmentId, clientId, petId, petName, lastName, phone, visitReason, appointmentDate, createDateTime, updateDateTime "
			+ "FROM newappointmentinfo "
			+ "WHERE petName = ? "
			+ "AND lastName = ?";
	
	private static String insertClient = "INSERT INTO clientinfo (firstName, lastName, phone, address, email) "
			+ "VALUES "
			+ "(?, ?, ?, ?, ?)";
	
	private static String selectNewClientId = "SELECT LAST_INSERT_ID() AS clientId";
	
	private static String insertPet = "INSERT INTO newpetinfo ( petName, age, sex, FIX, breed, clientId) "
			+ "VALUES "
			+ "( ?, ?, ?, ?, ?, ?)";
	
	private static String selectNewPetId = "SELECT LAST_INSERT_ID() AS petId";
	
	private static String insertAppointment = "INSERT INTO newappointmentinfo (clientId, petName,  lastName, phone, visitReason, appointmentDate, petId) "
			+ "VALUES " 
			+ "(?, ?, ?, ?, ?, ?, ?)";
	
	private static String selectNewAppointmentId = "SELECT LAST_INSERT_ID() AS appointmentId";
	
	private static String updateClientById = "UPDATE clientinfo "
			+ "SET firstName = ?, "
			+ "lastName = ?, "
			+ "phone = ?, "
			+ "address = ?, "
			+ "email = ? "
			+ "WHERE clientId = ? ";
	
	private static String selectPetsByPetId = "SELECT newpetinfo.clientId, petId,  lastName, phone, petName, age, sex, Fix, breed "
			+ "FROM newpetinfo "
			+ "JOIN clientinfo "
			+ "ON clientinfo.clientId = newpetinfo.clientId "
			+ "WHERE petId = ?";
	
	private static String updatePetsByPetId = "UPDATE newpetinfo "
			+ "SET petName = ?, "
			+ "age = ?, "
			+ "sex = ?, "
			+ "FIX = ?, "
			+ "Breed = ? "
			+ "WHERE petId = ? ";
	
	private static String updateAppointmentsById = "UPDATE newappointmentinfo "
			+ "SET petName = ?, "
			+ "visitReason = ?, "
			+ "appointmentDate = ? "
			+ "WHERE appointmentId = ? ";
	
	private static String deleteAppointmentById = "DELETE FROM newappointmentinfo " + "WHERE appointmentid = ?";
	
	private static String selectAppointmentsByPetId = "SELECT appointmentId, clientId, petId, petName, lastName, phone, visitReason, appointmentDate, updateDateTime, createDateTime "
			+ "FROM newappointmentinfo "
			+ "WHERE petId = ?";
	
	// Admin
	
	@Override
	public List<Client> getClients() {
		List<Client> myClients = new ArrayList<Client>();
		ResultSet result = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllClients);
			myClients = makeClient(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myClients;
	}

	private List<Client> makeClient(ResultSet result) {
		List<Client> myClients = new ArrayList<Client>();

		try {
			while (result.next()) {
				Client client = new Client();

				client.setClientId(result.getInt("clientId"));
				client.setFirstName(result.getString("firstName"));
				client.setLastName(result.getString("lastName"));
				client.setPhoneNumber(result.getString("phone"));
				client.setAddress(result.getString("address"));
				client.setEmail(result.getString("email"));
				client.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
				client.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));
				
				
				myClients.add(client);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return myClients;
	}
	
	@Override
	public List<Client> getClientsById(Integer clientId) {
		List<Client> myClients = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectClientsById);
			
			ps.setInt(1, clientId);
			
			result = ps.executeQuery();
			myClients = makeClient(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myClients;
	}

	@Override
	public List<Client> getClientsByPhoneNumber(String phoneNumber) {
		List<Client> myClients = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectClientsByPhoneNumber);
			
			ps.setString(1, phoneNumber);
			
			result = ps.executeQuery();
			myClients = makeClient(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myClients;
	}
	
	@Override
	public List<Client> getClientsByLastName(String lastName) {
		List<Client> myClients = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectClientsByLastName);
			ps.setString(1, lastName);
			
			result = ps.executeQuery();
			myClients = makeClient(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myClients;
	}

	
	@Override
	public List<Client> getPetsByName(String petName, String lastName) {
		List<Client> myPets = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectPetsByName);
			ps.setString(1, petName);
			ps.setString(2, lastName);;
			
			result = ps.executeQuery();
			myPets = makePet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myPets;
	}

	private List<Client> makePet(ResultSet result) {
		List<Client> myPets = new ArrayList<Client>();

		try {
			while (result.next()) {
				Client pet = new Client();

				pet.setClientId(result.getInt("clientId"));
				pet.setPetId(result.getInt("petId"));
				pet.setPetName(result.getString("petName"));
				pet.setLastName(result.getString("lastName"));
				pet.setPhoneNumber(result.getString("phone"));
				
				pet.setPetAge(result.getInt("age"));
				
				String genderString = result.getString("sex");
				pet.setGender(Gender.convertStringToGender(genderString));
				String statusString = result.getString("FIX");
				pet.setFixedStatus(FixedStatus.convertStringToStatus(statusString));
				
				pet.setBreed(result.getString("breed"));

				myPets.add(pet);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return myPets;
	}

	
	@Override
	public List<Client> getPetsByClientId(Integer clientId) {
		List<Client> myPets = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectPetsByClientId);
			
			ps.setInt(1, clientId);
			
			result = ps.executeQuery();
			myPets = makePet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myPets;
	}

	@Override
	public List<Client> getPetsByBreed(String breed) {
		List<Client> myPets = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectPetsByBreed);
			ps.setString(1, breed);
			
			result = ps.executeQuery();
			myPets = makePet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myPets;
	}

	@Override
	public List<Client> getPets() {
		List<Client> myPets = new ArrayList<Client>();
		ResultSet result = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllPets);
			myPets = makePet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myPets;
	}

	@Override
	public List<Client> getAppointments() {
		List<Client> myAppointments = new ArrayList<Client>();
		ResultSet result = null;
		Statement statement = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectAllAppointments);
			myAppointments = makeAppointment(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myAppointments;
	}

	private List<Client> makeAppointment(ResultSet result) {
		List<Client> myAppointments = new ArrayList<Client>();

		try {
			while (result.next()) {
				Client appointment = new Client();

				appointment.setAppointmentId(result.getInt("appointmentId"));
				appointment.setClientId(result.getInt("clientId"));
				appointment.setPetId(result.getInt("petId"));
				appointment.setPetName(result.getString("petName"));
				appointment.setLastName(result.getString("lastName"));
				appointment.setPhoneNumber(result.getString("phone"));
				appointment.setVisitReason(result.getString("visitReason"));
				appointment.setAppointmentDate(result.getObject("appointmentDate", LocalDateTime.class));
				appointment.setUpdateDateTime(result.getObject("updateDateTime", LocalDateTime.class));
				appointment.setCreateDateTime(result.getObject("createDateTime", LocalDateTime.class));

				myAppointments.add(appointment);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return myAppointments;
	}

	@Override
	public List<Client> getAppointmentsById(Integer appointmentId) {
		List<Client> myAppointments = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectAppointmentsById);
			ps.setInt(1, appointmentId);
			
			result = ps.executeQuery();
			myAppointments = makeAppointment(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myAppointments;
	}

	@Override
	public List<Client> getAppointmentsByName(String petName, String lastName) {
		List<Client> myAppointments = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectAppointmentsByName);
			ps.setString(1, petName);
			ps.setString(2, lastName);;
			
			result = ps.executeQuery();
			myAppointments = makeAppointment(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myAppointments;
	}

	@Override
	public Client createClient(Client newClient) {
		int updateRowCount = 0;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		try {
			ps = conn.prepareStatement(insertClient);
			
			ps.setString(1, newClient.getFirstName());
			ps.setString(2, newClient.getLastName());
			ps.setString(3, newClient.getPhoneNumber());
			ps.setString(4, newClient.getAddress());
			ps.setString(5, newClient.getEmail());
			updateRowCount = ps.executeUpdate();
			System.out.println("insert row count: " + updateRowCount);
			
			
			int clientId = getNewClientId(conn);
			newClient.setClientId(clientId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return newClient;
	}

	private int getNewClientId(Connection conn) {
		ResultSet result = null;
		Statement statement = null;
		int newClientId = 0;
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectNewClientId);
			
			while(result.next()) {
				newClientId = result.getInt("clientId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newClientId;
	}

	@Override
	public Client createPet(Client newPet) {
			
		int updateRowCount = 0;
		PreparedStatement ps = null;
		
		Connection conn = MariaDbUtil.getConnection();
		try {
			ps = conn.prepareStatement(insertPet);
			
			
			ps.setString(1, newPet.getPetName());
			ps.setInt(2, newPet.getPetAge());
			ps.setString(3, newPet.getGender().toString());
			ps.setString(4, newPet.getFixedStatus().toString());
			ps.setString(5,  newPet.getBreed());
			ps.setInt(6, newPet.getClientId());
			updateRowCount = ps.executeUpdate();
			System.out.println("insert row count: " + updateRowCount);
			
			
			int petId = getNewPetId(conn);
			newPet.setPetId(petId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return newPet;
	}
	
	private int getNewPetId(Connection conn) {
	ResultSet result = null;
	Statement statement = null;
	int newPetId = 0;
	
	try {
		statement = conn.createStatement();
		result = statement.executeQuery(selectNewPetId);
		
		while(result.next()) {
			newPetId = result.getInt("petId");
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	return newPetId;
}

	@Override
	public Client createAppointment(Client newAppointment) {
		int updateRowCount = 0;
		PreparedStatement ps = null;
		Connection conn = MariaDbUtil.getConnection();	
		
		try {
			ps = conn.prepareStatement(insertAppointment);
			
			ps.setInt(1,  newAppointment.getClientId());
			
			ps.setString(2, newAppointment.getPetName());
			ps.setString(3, newAppointment.getLastName());
			ps.setString(4, newAppointment.getPhoneNumber());
			ps.setString(5, newAppointment.getVisitReason());
			ps.setTimestamp(6, Timestamp.valueOf(newAppointment.getAppointmentDate()));
			ps.setInt(7,  newAppointment.getPetId());
			updateRowCount = ps.executeUpdate();
			System.out.println("insert row count: " + updateRowCount);
			
			
			int appointmentId = getNewAppointmentId(conn);
			newAppointment.setAppointmentId(appointmentId);
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return newAppointment;
	}

	private int getNewAppointmentId(Connection conn) {
		ResultSet result = null;
		Statement statement = null;
		int newAppointmentId = 0;
		
		try {
			statement = conn.createStatement();
			result = statement.executeQuery(selectNewAppointmentId);
			
			while(result.next()) {
				newAppointmentId = result.getInt("appointmentId");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return newAppointmentId;
	}

	
	@Override
	public Client updateClient(Client updateClient) {
		List<Client> clients = this.getClientsById(updateClient.getClientId());
		

		if (clients.size() > 0) {
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection conn = MariaDbUtil.getConnection();

			try {
				ps = conn.prepareStatement(updateClientById);
				ps.setString(1, updateClient.getFirstName());
				ps.setString(2, updateClient.getLastName());
				ps.setString(3, updateClient.getPhoneNumber());
				ps.setString(4, updateClient.getAddress());
				ps.setString(5, updateClient.getEmail());
				ps.setInt(6,  updateClient.getClientId());
				
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return updateClient;
	}

	
	@Override
	public Client updatePets(Client updatePets) {
		List<Client> pets = this.getPetsByPetId(updatePets.getPetId());
		

		if (pets.size() > 0) {
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection conn = MariaDbUtil.getConnection();

			try {
				ps = conn.prepareStatement(updatePetsByPetId);
				ps.setString(1, updatePets.getPetName());
				ps.setInt(2, updatePets.getPetAge());
				ps.setString(3, updatePets.getGender().toString());
				ps.setString(4, updatePets.getFixedStatus().toString());
				ps.setString(5, updatePets.getBreed());
				ps.setInt(6,  updatePets.getPetId());
				
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return updatePets;
	}

	@Override
	public List<Client> getPetsByPetId(Integer petId) {
		List<Client> myPets = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectPetsByPetId);
			
			ps.setInt(1, petId);
			
			result = ps.executeQuery();
			myPets = makePet(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myPets;
	}

	
	@Override
	public Client updateAppointment(Client updateAppointment) {
		List<Client> appointments = this.getAppointmentsById(updateAppointment.getAppointmentId());
		

		if (appointments.size() > 0) {
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection conn = MariaDbUtil.getConnection();

			try {
				ps = conn.prepareStatement(updateAppointmentsById);
				ps.setString(1, updateAppointment.getPetName());
				ps.setString(2, updateAppointment.getVisitReason());
				ps.setObject(3, updateAppointment.getAppointmentDate());
				ps.setInt(4,  updateAppointment.getAppointmentId());
				
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return updateAppointment;
	}

	
	@Override
	public Client deleteAppointmentById(Integer appointmentId) {
		List<Client> appointments = this.getAppointmentsById(appointmentId);
		Client appointmentToDelete = null;

		if (appointments.size() > 0) {
			appointmentToDelete = appointments.get(0);
			int updateRowCount = 0;
			PreparedStatement ps = null;

			Connection conn = MariaDbUtil.getConnection();

			try {
				ps = conn.prepareStatement(deleteAppointmentById);
				ps.setInt(1, appointmentId);
				updateRowCount = ps.executeUpdate();
				System.out.println("update row count: " + updateRowCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return appointmentToDelete;
	}

	@Override
	public List<Client> getAppointmentsByPetId(Integer petId) {
		List<Client> myAppointments = new ArrayList<Client>();
		ResultSet result = null;
		PreparedStatement ps = null;

		Connection conn = MariaDbUtil.getConnection();

		try {
			ps = conn.prepareStatement(selectAppointmentsByPetId);
			
			ps.setInt(1, petId);
			
			result = ps.executeQuery();
			myAppointments = makeAppointment(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return myAppointments;
	}
}

