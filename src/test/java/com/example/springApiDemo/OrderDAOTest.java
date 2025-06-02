package com.example.springApiDemo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.example.springapidemo.northwindAPI.models.CustomerDAO;
import com.example.springapidemo.northwindAPI.models.DB_Connection;
import com.example.springapidemo.northwindAPI.models.OrderDAO;

class OrderDAOTest {
    private static OrderDAO orderDAO;

    /**
	 * Stellt die Datenbankverbindung vor allen Tests her.
	 * Wird einmalig vor allen Tests aufgerufen.
	 */
    @BeforeAll
    static void setup() {
    	System.out.println("Statische Datenbankverbindung wird hergestellt...");
        try {
			orderDAO = new OrderDAO(DB_Connection.getDataSource());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("Die Datenbankverbindung konnte nicht hergestellt werden: " + e.getMessage());
		}
    }

    /**
     * Testet verschiedene Summen der Bestellungen
     */
	@Test
	void test() {
		fail("Not yet implemented");
	}

}
