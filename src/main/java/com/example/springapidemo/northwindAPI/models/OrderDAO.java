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

public class OrderDAO {
	private final DataSource dataSource;
	private final JdbcTemplate jdbc;
	private static Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

	public OrderDAO(DataSource ds) {
		dataSource = ds;
		jdbc = new JdbcTemplate(dataSource);
	}

	/**
	 * Liefert eine Liste aller Kunden aus der Datenbank.
	 * 
	 * @return Eine Liste von Customer-Objekten, die alle Kunden repräsentieren.
	 */
	public List<Order> getList() {

		try {
			List<Order> result = jdbc.query(
			//@formatter:off
			    "SELECT "
			    + "o.order_id, "
			    + "o.customer_id, "
			    + "o.employee_id, "
			    + "o.order_date, "
			    + "o.required_date, "
			    + "o.shipped_date, "
			    + "o.ship_via, "
			    + "o.freight, "
			    + "o.ship_name, "
			    + "o.ship_address, "
			    + "o.ship_city, "
			    + "o.ship_region, "
			    + "o.ship_postal_code, "
			    + "o.ship_country, "
			    + "EXTRACT(YEAR FROM o.order_date) AS order_year, "
			    + "EXTRACT(MONTH FROM o.order_date) AS order_month, "
			    + "ROUND(SUM(od.unit_price * od.quantity * (1 - od.discount))::numeric,2) AS total_price " //Konvertiert den Gesamtpreis in einen numerischen Wert mit 2 Dezimalstellen da Round Double mit Int nicht unterstützt
			    + "FROM northwind.orders o "
			    +"JOIN northwind.order_details od ON o.order_id = od.order_id "
			    + "GROUP BY o.order_id, o.customer_id, o.employee_id, o.order_date, o.required_date, o.shipped_date,\r\n"
			    + "         o.ship_via, o.freight, o.ship_name, o.ship_address, o.ship_city, o.ship_region,\r\n"
			    + "         o.ship_postal_code, o.ship_country\r\n"
			    + "ORDER BY o.order_date ASC;\r\n"
			    + "",
			    (rs, rowNum) -> new Order(
			        rs.getInt("order_id"),
			        rs.getString("customer_id"),
			        rs.getInt("employee_id"),
			        rs.getDate("order_date"),
			        rs.getDate("required_date"),
			        rs.getDate("shipped_date"),
			        rs.getInt("ship_via"),
			        rs.getDouble("freight"),
			        rs.getString("ship_name"),
			        rs.getString("ship_address"),
			        rs.getString("ship_city"),
			        rs.getString("ship_region"),
			        rs.getString("ship_postal_code"),
			        rs.getString("ship_country"),
			        rs.getInt("order_year"),
			        rs.getInt("order_month"),
			        rs.getDouble("total_price")
			    ));
			return result;
		} catch (DataAccessException e) {	//DataAccessException ist eine abstrakte Klasse von Spring. Der genaue Typ  wird über e.getClass().getName() ermittelt.
		    // Loggen der Fehlermeldung
			logger.error("Fehler beim Abrufen der Bestellung."+e.getClass().getName(), e.getMessage());
		    System.err.println("Fehler beim Abrufen der Bestellung: " + e.getMessage());
		    System.err.println("Exception Type: " + e.getClass().getName());
		    throw new RuntimeException("Fehler beim Abrufen der Bestellung", e);
		}
	//@formatter:on

	}

	/**
	 * Liefert eine Bestellung anhand der Bestell-ID aus der Datenbank.
	 * 
	 * @param order_id Die ID der Bestellung, die abgerufen werden soll.
	 * @return Eine Liste von Order-Objekten, die die Bestellung repräsentieren.
	 */
	public Order getOrderById(int order_id) {

		try {
			Order result = (Order) jdbc.query( //Casting zu Order, da query() eine Liste zurückgibt, die in diesem Fall nur ein Element enthält.
			//@formatter:off
			    "SELECT "
			    + "o.order_id, "
			    + "o.customer_id, "
			    + "o.employee_id, "
			    + "o.order_date, "
			    + "o.required_date, "
			    + "o.shipped_date, "
			    + "o.ship_via, "
			    + "o.freight, "
			    + "o.ship_name, "
			    + "o.ship_address, "
			    + "o.ship_city, "
			    + "o.ship_region, "
			    + "o.ship_postal_code, "
			    + "o.ship_country, "
			    + "EXTRACT(YEAR FROM o.order_date) AS order_year, "
			    + "EXTRACT(MONTH FROM o.order_date) AS order_month, "
			    + "ROUND(SUM(od.unit_price * od.quantity * (1 - od.discount))::numeric,2) AS total_price " //Konvertiert den Gesamtpreis in einen numerischen Wert mit 2 Dezimalstellen da Round Double mit Int nicht unterstützt
			    + "FROM northwind.orders o "
			    +"JOIN northwind.order_details od ON o.order_id = od.order_id "
			    +"WHERE o.order_id = ? "
			    + "GROUP BY o.order_id, o.customer_id, o.employee_id, o.order_date, o.required_date, o.shipped_date",
			    (rs, rowNum) -> new Order(
			        rs.getInt("order_id"),
			        rs.getString("customer_id"),
			        rs.getInt("employee_id"),
			        rs.getDate("order_date"),
			        rs.getDate("required_date"),
			        rs.getDate("shipped_date"),
			        rs.getInt("ship_via"),
			        rs.getDouble("freight"),
			        rs.getString("ship_name"),
			        rs.getString("ship_address"),
			        rs.getString("ship_city"),
			        rs.getString("ship_region"),
			        rs.getString("ship_postal_code"),
			        rs.getString("ship_country"),
			        rs.getInt("order_year"),
			        rs.getInt("order_month"),
			        rs.getDouble("total_price")
			    ),order_id);
			return result;
		} catch (DataAccessException e) {	//DataAccessException ist eine abstrakte Klasse von Spring. Der genaue Typ  wird über e.getClass().getName() ermittelt.
		    // Loggen der Fehlermeldung
			logger.error("Fehler beim Abrufen der Bestellung."+e.getClass().getName(), e.getMessage());
		    System.err.println("Fehler beim Abrufen der Bestellung: " + e.getMessage());
		    System.err.println("Exception Type: " + e.getClass().getName());
		    throw new RuntimeException("Fehler beim Abrufen der Bestellung", e);
		}
	//@formatter:on

	}
	public List<Map<String, Object>> getOrdersByYearWithCustomers(int year) {
//		if (!year.equals("%")) {
//			year = "%" + year + "%";
//		}

		// Die Abfrage liefert Bestellungen für ein bestimmtes Jahr mit den zugehörigen
		// Kundeninformationen.
		// Die Spalten order_year und order_month werden aus dem order_date extrahiert.
	// o = orders, od = order_details, p = products
	//@formatter:off
	return jdbc.queryForList(
		    "SELECT o.order_id, " +
		    "o.customer_id, " +
		    "c.company_name, " +
		    "o.employee_id, " +
		    "o.order_date, " +
		    "(EXTRACT(YEAR FROM o.order_date)) AS order_year, " +
		    "(EXTRACT(MONTH FROM o.order_date)) AS order_month, " + // Komma hinzugefügt
		    "ROUND(SUM(od.unit_price * (1 - od.discount) * od.quantity)::numeric, 2) AS total " + 
		    "FROM northwind.orders o " +
		    "JOIN northwind.customers c ON o.customer_id = c.customer_id " + 
		    "JOIN northwind.order_details od ON o.order_id = od.order_id " + 
		    "WHERE (EXTRACT(YEAR FROM o.order_date)) = CAST(? AS INTEGER) " +
		    "GROUP BY o.order_id, o.customer_id, c.company_name, o.employee_id, o.order_date, order_year, order_month " + // `GROUP BY` hinzugefügt
		    "ORDER BY o.order_date ASC",
		    year);
	}//@formatter:on
	
	
	/**
	 * Liefert Bestellungen für einen bestimmten Kunden.
	 * 
	 * @param customer_id Die ID des Kunden, dessen Bestellungen abgerufen werden
	 *                    sollen.
	 * @return Eine Liste von Maps, die die Bestellungen und zugehörigen Produktdetails
	 *         enthalten.
	 */
	public List<Map<String, Object>> getOrdersByCustomer(String customer_id) {
		if (!customer_id.equals("%"))
			customer_id = "%" + customer_id + "%";
		
		// o = orders, od = order_details, p = products
		//@formatter:off
			return jdbc.queryForList(
				    "SELECT o.order_id, " +
				    "o.customer_id, " +
				    "o.employee_id, " +
				    "o.order_date, " +
				    "o.required_date, " +
				    "o.shipped_date, " +
				    "o.ship_via, " +
				    "o.freight, " +
				    "o.ship_name, " +
				    "o.ship_address, " +
				    "o.ship_city, " +
				    "o.ship_region, " +
				    "o.ship_postal_code, " +
				    "o.ship_country, " +
				    "od.product_id, " +       // Produkt-ID aus Order_Details-Tabelle
				    "p.product_name, " +      // Produktname aus Products-Tabelle
				    "od.unit_price, " +       // Einzelpreis aus Order_Details-Tabelle
				    "od.quantity, " +         // Menge aus Order_Details-Tabelle
				    "od.discount, " +          // Rabatt aus Order_Details-Tabelle
				    "(od.unit_price * od.quantity * (1 - od.discount)) AS total_price " + // Gesamtpreis berechnen
				    "FROM northwind.orders o " +
				    "JOIN northwind.order_details od ON o.order_id = od.order_id " + // Verknüpft Bestellungen mit den Order_Details (od)
				    "JOIN northwind.products p ON od.product_id = p.product_id " +   //  Verknüpft Artikel aus der Order_Details-Tabelle mit der Products-Tabelle (p)
				    "WHERE o.customer_id LIKE ?",
				    customer_id);
		}

		
}
