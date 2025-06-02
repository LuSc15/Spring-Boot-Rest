package com.example.springapidemo.northwindAPI;

import java.sql.SQLException;
import java.util.HashMap;
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

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerController() {
        try {
            customerDAO = new CustomerDAO(DB_Connection.getDataSource());
        } catch (SQLException e) {
            throw new RuntimeException("Fehler beim Erstellen von CustomerDAO", e);
        }
    }

	@GetMapping("/getCustomer")
	public List<Customer> getCustomer(@RequestParam(value = "Contact_name", defaultValue = "%") String contact_name, 
			@RequestParam(value = "Id", defaultValue = "%") String id,
			@RequestParam(value = "Company_name", defaultValue = "%") String company_name,
			@RequestParam(value = "Contact_title", defaultValue = "%") String contact_title,
			@RequestParam(value = "Address", defaultValue = "%") String address,
			@RequestParam(value = "City", defaultValue = "%") String city,
			@RequestParam(value = "Region", defaultValue = "%") String region,
			@RequestParam(value = "Postal_code", defaultValue = "%") String postal_code,
			@RequestParam(value = "Country", defaultValue = "%") String country,
			@RequestParam(value = "Phone", defaultValue = "%") String phone,
			@RequestParam(value = "Fax", defaultValue = "%") String fax) {

		// Erstellen einer Map mit den Parametern, die an die DAO übergeben werden
		Map<String, String> params = new HashMap<>();
		String[] keys = {"contact_name", "id", "company_name", "contact_title", "address", "city", 
		                 "region", "postal_code", "country", "phone", "fax"};
		String[] values = {contact_name, id, company_name, contact_title, address, city, 
		                   region, postal_code, country, phone, fax};

		for (int i = 0; i < keys.length; i++) {
		    params.put(keys[i], "%".equals(values[i]) ? values[i] : "%" + values[i] + "%");
		}

		
		List<Customer> result = customerDAO.getList(params);
		return result;
	}
	

}


