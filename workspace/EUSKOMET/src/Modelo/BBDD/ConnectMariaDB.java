package Modelo.BBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectMariaDB {
	private static ConnectMariaDB ourInstance = new ConnectMariaDB();

	// init database konstanteak
	private static final String DATABASE_DRIVER = "org.mariadb.jdbc.Driver";
//	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/euskomet";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "1234";
	private static final String MAX_POOL = "250";

	// konexioa
	private Connection konexioa;
	// private Properties propietateak;

	// Singleton
	public static ConnectMariaDB getInstance() {
		return ourInstance;
	}

	private ConnectMariaDB() {
	}

	/*
	 * private Properties getProperties() { if (propietateak == null) { propietateak
	 * = new Properties(); propietateak.setProperty("user", USERNAME);
	 * propietateak.setProperty("password", PASSWORD);
	 * propietateak.setProperty("MaxPooledStatements", MAX_POOL); } return
	 * propietateak; }
	 */
	// konektatu datubasera
	public Connection konektatu(String bbdd) {
		if (konexioa == null) {
			try {
				Class.forName(DATABASE_DRIVER);
				// konexioa = (Connection) DriverManager.getConnection(DATABASE_URL,
				// getProperties());
				konexioa = (Connection) DriverManager.getConnection(DATABASE_URL + bbdd, USERNAME, PASSWORD);
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Konexioa ezin izan da burutu.");
				e.printStackTrace();
			}
		}
		return konexioa;
	}

	// datubasetik deskonektatu
	public void deskonektatu() {
		if (konexioa != null) {
			try {
				konexioa.close();
				konexioa = null;
				System.out.println("Konexioa itxi da");
			} catch (SQLException e) {
				System.err.println("Ezin izan da deskonektatu.");
				e.printStackTrace();
			}
		}
	}
}


