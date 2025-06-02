package com.example.springapidemo.northwindAPI.models;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

public class CustomerDAO {
	private final DataSource dataSource;
	private final JdbcTemplate jdbc;
	private static Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	public CustomerDAO(DataSource ds) {
		dataSource = ds;
		jdbc = new JdbcTemplate(dataSource);
	}


	/**
	 * Liefert eine Liste aller Kunden aus der Datenbank.
	 * 
	 * @return Eine Liste von Customer-Objekten, die alle Kunden repräsentieren.
	 */
	public List<Customer> getList() {
		
		try {
			List<Customer> result = jdbc.query(
			//@formatter:off
			    "SELECT * FROM northwind.customers",
			    (rs, rowNum) -> new Customer(
			        rs.getString("customer_id"),
			        rs.getString("company_name"),
			        rs.getString("contact_name"),
			        rs.getString("contact_title"),
			        rs.getString("address"),
			        rs.getString("city"),
			        rs.getString("region"),
			        rs.getString("postal_code"),
			        rs.getString("country"),
			        rs.getString("phone"),
			        rs.getString("fax")
			    ));
			return result;
		} catch (DataAccessException e) {	//DataAccessException ist eine abstrakte Klasse von Spring. Der genaue Typ  wird über e.getClass().getName() ermittelt.
		    // Loggen der Fehlermeldung
			logger.error("Fehler beim Abrufen der Kundenliste."+e.getClass().getName(), e.getMessage());
		    System.err.println("Fehler beim Abrufen der Kundenliste: " + e.getMessage());
		    System.err.println("Exception Type: " + e.getClass().getName());
		    throw new RuntimeException("Fehler beim Abrufen der Kundenliste", e);
		}
	//@formatter:on
			
	}

	/**
	 *  Liefert eine Liste von Kunden, die den angegebenen Suchkriterien entsprechen.
	 * @param params Ein Map mit Suchkriterien, die auf die Kunden angewendet werden sollen.
	 * Gültige Schlüssel sind:
	 * customer_id, company_name, contact_name, contact_title, address, city, region, postal_code, country, phone, fax.
	 * @return
	 */
	public List<Customer> getList(Map<String, String> params) {
		
		String cusstomer_id = params.getOrDefault("customer_id", "%");
		String company_name = params.getOrDefault("company_name", "%");
		String contact_name = params.getOrDefault("contact_name", "%");
		String contact_title = params.getOrDefault("contact_title", "%");
		String address = params.getOrDefault("address", "%");
		String city = params.getOrDefault("city", "%");
		String region = params.getOrDefault("region", "%");
		String postal_code = params.getOrDefault("postal_code", "%");
		String country = params.getOrDefault("country", "%");
		String phone = params.getOrDefault("phone", "%");
		String fax = params.getOrDefault("fax", "%");

		try {
			List<Customer> result = jdbc.query(
			//@formatter:off
			    "SELECT "
			    + "customer_id, "
			    + "company_name, "
			    + "contact_name, "
			    + "contact_title, "
			    + "address, "
			    + "city, "
			    + "region, "
			    + "postal_code, "
			    + "country, "
			    + "phone, "
			    + "fax FROM northwind.customers "
			    + "WHERE "
			    + "customer_id LIKE ? AND "
			    + "company_name LIKE ? AND "
			    + "contact_name LIKE ? AND "
			    + "contact_title LIKE ? AND "
			    + "address LIKE ? AND "
			    + "city LIKE ? AND "
			    + "(region IS NULL OR region LIKE ?) AND "
			    + "postal_code LIKE ? AND "
			    + "country LIKE ? AND "
			    + "phone LIKE ? AND "
			    + "(fax IS NULL OR fax LIKE ?)",
			    (rs, rowNum) -> new Customer(
			        rs.getString("customer_id"),
			        rs.getString("company_name"),
			        rs.getString("contact_name"),
			        rs.getString("contact_title"),
			        rs.getString("address"),
			        rs.getString("city"),
			        rs.getString("region"),
			        rs.getString("postal_code"),
			        rs.getString("country"),
			        rs.getString("phone"),
			        rs.getString("fax")
			    ),cusstomer_id, company_name, contact_name, contact_title, address, city, region, postal_code, country, phone, fax);
	//@formatter:on
			return result;
		} catch (DataAccessException e) {	//DataAccessException ist eine abstrakte Klasse von Spring. Der genaue Typ  wird über e.getClass().getName() ermittelt.
		    // Loggen der Fehlermeldung
			logger.error("Fehler beim Abrufen der Kundenliste."+e.getClass().getName(), e.getMessage());
		    System.err.println("Fehler beim Abrufen der Kundenliste: " + e.getMessage());
		    System.err.println("Exception Type: " + e.getClass().getName());
		    throw new RuntimeException("Fehler beim Abrufen der Kundenliste", e);


		}
	}
}
