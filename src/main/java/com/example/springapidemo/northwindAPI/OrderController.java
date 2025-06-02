package com.example.springapidemo.northwindAPI;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapidemo.northwindAPI.models.Customer;
import com.example.springapidemo.northwindAPI.models.CustomerDAO;
import com.example.springapidemo.northwindAPI.models.DB_Connection;
import com.example.springapidemo.northwindAPI.models.Order;
import com.example.springapidemo.northwindAPI.models.OrderDAO;
import com.example.springapidemo.northwindAPI.models.ExceptionHandling.InvalidYearException;

import jakarta.servlet.http.HttpServletRequest;

@RestController
//@CrossOrigin(origins = {"http://localhost:3000"})
public class OrderController {
    private final OrderDAO orderDAO;

    @Autowired
    public OrderController() {
        try {
            orderDAO = new OrderDAO(DB_Connection.getDataSource());
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Erstellen von CustomerDAO", e);
        }
    }
    
    /**
     * Liefert eine Liste aller Bestellungen aus der Datenbank.
     * @return Eine Liste von Order-Objekten, die alle Bestellungen beinhaltet.
     */
    	@GetMapping("/getOrders")
    		public List<Order> getOrders(){
			return orderDAO.getList();
		}
    	/**
    	 * Liefert die Bestellung zu der angegebenen Bestell-ID.
    	 * @param order_id Die ID der zu ermittelnden Bestellung.
    	 * @return Ein Order-Objekt, das die Bestellung repräsentiert.
    	 */
    	
    	@GetMapping("/getOrderById")
    	public Order getOrderById(@RequestParam(value = "order_id", defaultValue = "%") int order_id) {	
			return orderDAO.getOrderById(order_id);
    	}	

	@GetMapping("/getOrdersByCustomer")
	public List<Map<String, Object>> getOrdersByCustomer(
			@RequestParam(value = "customer_id", defaultValue = "%") String customer_id) {
		return orderDAO.getOrdersByCustomer(customer_id);
		}//@formatter:on
	
	@GetMapping("/getOrdersByYear")
	public List<Map<String, Object>> getOrdersByYearWithCustomers(
			@RequestParam(value = "year", defaultValue = "%") String year, HttpServletRequest request) {
		try {

			Integer parsedYear = year.equals("%") ? null : Integer.parseInt(year);
			return orderDAO.getOrdersByYearWithCustomers(parsedYear);

		}
		catch (NumberFormatException e) {
			throw new InvalidYearException("Ungültiges Jahr: " + year, request);
		}
	}
		


}
