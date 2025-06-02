package com.example.springApiDemo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.springapidemo.northwindAPI.models.Customer;
import com.example.springapidemo.northwindAPI.models.CustomerDAO;
import com.example.springapidemo.northwindAPI.models.DB_Connection;

class CustomerDAOTest {
	//DAO-Objekt statisch initialisieren, damit nicht jeder Test eine neue Datenbankverbindung aufbauen muss
    private static CustomerDAO customerDAO;

    /**
	 * Stellt die Datenbankverbindung vor allen Tests her.
	 * Wird einmalig vor allen Tests aufgerufen.
	 */
    @BeforeAll
    static void setup() {
    	System.out.println("Statische Datenbankverbindung wird hergestellt...");
        try {
			customerDAO = new CustomerDAO(DB_Connection.getDataSource());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Die Datenbankverbindung konnte nicht hergestellt werden: " + e.getMessage());
		}
    }
    
    /**
	 * Testet die Verbindung zur Datenbank.
	 * Wenn die Verbindung nicht hergestellt werden kann, schlägt der Test fehl.
	 */
@Test
void connectionTest() {
//		try {
//			Connection conn = CustomerDAO.getDataSource().getConnection();
//		} catch (SQLException e) {
//			// Wenn eine Exception auftritt, ist die Verbindung nicht erfolgreich
//			fail("Die Verbindung zur Datenbank konnte nicht hergestellt werden: " + e.getMessage());
//		}
assertDoesNotThrow(() -> {
	System.out.println("Verbindung zur Datenbank wird getestet...");
			Connection conn = DB_Connection.getDataSource().getConnection();
			conn.close(); // Verbindung schließen, um Ressourcen freizugeben
			System.out.println("Testverbindung zur Datenbank erfolgreich! Verbindung wurde geschlossen.");
		}, "Die Verbindung zur Datenbank sollte erfolgreich sein.");
	}

	/**
	 * Testet ob die Liste der Kunden nicht leer ist.
	 */
	@Test
	void kundenListeNichtLeer() {
		List<Customer> kundenListe = customerDAO.getList();
		assertFalse(kundenListe.isEmpty(), "Die Kundenliste sollte nicht leer sein.");
	}

	/**
	 * Testet ob die Anzahl der Kunden in der Liste korrekt ist.
	 */
	@Test
	void kundenanzahlTest() {
		List<Customer> kundenListe = customerDAO.getList();

		// Prüfen ob die Anzahl der Kunden in der Liste korrekt ist
		assertEquals(91,kundenListe.size(), "Die Anzahl der Kunden sollte 91 sein.");
		//fail("Not yet implemented");
	}
	
	/**
	 * Prüft ob die Anzahl der Kunden mit mindestens einem Nullwert korrekt ist.
	 */
	@Test
	void nullKorrektErkennen() {
		List<Customer> kundenListe = customerDAO.getList();
System.out.println("Anzahl der Kunden in der Liste: " + kundenListe.size());
		List<Customer> listeMitNullwerten = kundenListe.stream()
	    .filter(c -> c.getCompany_name() == null || 
	                 c.getContact_name() == null ||
	                 c.getContact_title() == null ||
	                 c.getRegion() == null ||
	                 c.getAddress() == null || 
	                 c.getCity() == null ||
	                 c.getPostal_code() == null ||
	                 c.getCountry() == null ||
	                 c.getPhone() == null ||
	                 c.getFax() == null)
	    .collect(Collectors.toList());

		assertEquals(72,listeMitNullwerten.size(), "Es wurden nicht 72 Einträge mit Nullwerten gefunden..");

	}

}
