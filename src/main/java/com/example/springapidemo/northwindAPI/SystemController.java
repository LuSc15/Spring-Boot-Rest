package com.example.springapidemo.northwindAPI;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.springapidemo.northwindAPI.models.Customer;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SystemController {
	 @Autowired
	  JdbcTemplate jdbcTemplate;

    @PostMapping("/resetDatabase")
    public Map<String,String> executeSqlScript() {
    	Map<String,String> result = new HashMap(); //Nicht Map.of verwenden, da die Map dann nicht unveränderlich ist.
    	
    	ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    	InputStream is = classloader.getResourceAsStream("Northwind in Postgre.sql");
    	
    	try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            StringBuilder sqlStatement = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                sqlStatement.append(line).append("\n");
            }

            // SQL-Befehl ausführen
            try {
	            jdbcTemplate.execute(sqlStatement.toString());
	            result.put("Ergebnis", "Datenbank erfolgreich zurückgesetzt.");
            } 
	    	//jdbcTemplate.execute gibt statt einer SQLException eine DataAccessException zurück.
            catch (DataAccessException e) {
                // Fehlerbehandlung
                e.printStackTrace();
                result.put("Ergebnis","Fehler beim Zurücksetzen der Datenbank: " + e.getMessage());
            }

            return result;
    	}


    	catch (IOException e) {
    		result.put("Ergebnis", "Fehler beim Lesen der SQL-Datei: " + e.getMessage());
            return result;
        }
    }
}
