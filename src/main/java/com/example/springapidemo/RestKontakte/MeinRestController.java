package com.example.springapidemo.RestKontakte;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeinRestController {
	//@Autowired
	//private Environment env; //Für den Zugriff auf die Umgebungsvariablen wie DB-URL, DB-User und DB-Passwort
	 @Autowired
	  JdbcTemplate jdbcTemplate;

	@GetMapping("/getContacts")
	public List<Contact> greeting(@RequestParam(value = "name", defaultValue = "%") String name, 
			@RequestParam(value = "id", defaultValue = "0") String idx) {
		return jdbcTemplate.query(
			    "SELECT id, firstname, lastname, mail, phone, zipcode, city, gender FROM public.contacts WHERE firstname LIKE ?",
			    (rs, rowNum) -> {
			        Contact c = new Contact(rs.getLong("id"), rs.getString("firstname"), rs.getString("lastname"),rs.getString("mail"),
			        		rs.getString("phone"), rs.getString("zipcode"),rs.getString("city"),rs.getString("gender"));
			        System.out.println("Verarbeite Zeile " + rowNum + ": " + c.toString());
			        return c; // Wird direkt von Spring in einer Liste gesammelt, keine separaten Collections nötig.
			    }, name); //Parameter für den ?-Platzhalter im Statement.
	}
}
