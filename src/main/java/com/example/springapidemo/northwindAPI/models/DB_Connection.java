package com.example.springapidemo.northwindAPI.models;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public class DB_Connection {
	/**
	 * Stellt Verbindung zur Datenbank her.
	 * 
	 * @return Die DataSource zur Datenbank die in den Umgebungsvariablen
	 *         konfiguriert ist.
	 * @throws SQLException
	 */
	public static DataSource getDataSource() throws SQLException {
		PGSimpleDataSource dataSource = new PGSimpleDataSource();
		dataSource.setUrl(System.getenv("DB_URL"));
		dataSource.setUser(System.getenv("DB_USERNAME"));
		dataSource.setPassword(System.getenv("DB_PASSWORD"));

		// Verbindung testen
		try (Connection conn = dataSource.getConnection()) {
			System.out.println("Erfolgreich mit der Datenbank verbunden!");
		} catch (SQLException e) {
			System.out.println("Fehler beim Verbinden mit der Datenbank: " + e.getMessage());
			throw new SQLException("Fehler beim Verbinden mit der Datenbank: " + e.getMessage());
		}

		return dataSource;
	}
}
