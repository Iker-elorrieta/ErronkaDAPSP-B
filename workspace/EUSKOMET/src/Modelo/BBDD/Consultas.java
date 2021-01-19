package Modelo.BBDD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.Municipio;
import Modelo.Objetuak.Provincias;

public class Consultas {
	
	public static ArrayList<Provincias> consultaProvincias(String sql, String bbdd) {
		ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();

		Connection conn = null;
		Statement stmt = null;

		try {

			// Konexioa Ireki
			System.out.println("[Consultas] -- Conectando a la base de datos...");
			ConnectMariaDB MDK = ConnectMariaDB.getInstance();
			conn = MDK.konektatu(bbdd);

			// query edo SQL sententzia egikaritu
			System.out.println("[Consultas] -- Creando Statement..");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// Erantzunetik informazioa erauzi
			while (rs.next()) {
				// Bueltatu zutabeen izenen arabera
				int cod_prov = rs.getInt("cod_prov");
				String nombre = rs.getString("nombre");

				// Emaitzak pantailaratu
				Provincias prov = new Provincias(cod_prov, nombre);
				arrayProvincias.add(prov);
			}

			// Garbiketa
			rs.close();
			stmt.close();
			MDK.deskonektatu();

			System.out.println("[Consultas] -- Resultado devuelto");
			System.out.println("[Consultas] -- Conexión con la base de datos cerrada");
		} catch (SQLException se) {
			// JDBC erroreak
			se.printStackTrace();
		} catch (Exception e) {
			// Class.forName errorea
			e.printStackTrace();
		} finally {
			// itxi erabilitako errekurtsoak
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return arrayProvincias;
	}

	public static ArrayList<Municipio> consultaMunicipios(String sql, String bbdd) {
		ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();

		Connection conn = null;
		Statement stmt = null;

		try {

			// Konexioa Ireki
			System.out.println("[Consultas] -- Conectando a la base de datos...");
			ConnectMariaDB MDK = ConnectMariaDB.getInstance();
			conn = MDK.konektatu(bbdd);

			// query edo SQL sententzia egikaritu
			System.out.println("[Consultas] -- Creando Statement..");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// Erantzunetik informazioa erauzi
			while (rs.next()) {
				// Bueltatu zutabeen izenen arabera
				int cod_mun = rs.getInt("cod_mun");
				String nombre = rs.getString("nombre");
				int cod_prov = rs.getInt("cod_prov");

				// Emaitzak pantailaratu
				Municipio mun = new Municipio(cod_mun, nombre, cod_prov);
				arrayMunicipios.add(mun);
			}

			// Garbiketa
			rs.close();
			stmt.close();
			MDK.deskonektatu();

			System.out.println("[Consultas] -- Resultado devuelto");
			System.out.println("[Consultas] -- Conexión con la base de datos cerrada");
		} catch (SQLException se) {
			// JDBC erroreak
			se.printStackTrace();
		} catch (Exception e) {
			// Class.forName errorea
			e.printStackTrace();
		} finally {
			// itxi erabilitako errekurtsoak
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return arrayMunicipios;
	}

	public static ArrayList<Estazioa> consultaEstaciones(String sql, String bbdd) {

		ArrayList<Estazioa> arrayEstaciones = new ArrayList<Estazioa>();

		Connection conn = null;
		Statement stmt = null;

		try {

			// Konexioa Ireki
			System.out.println("[Consultas] -- Conectando a la base de datos...");
			ConnectMariaDB MDK = ConnectMariaDB.getInstance();
			conn = MDK.konektatu(bbdd);

			// query edo SQL sententzia egikaritu
			System.out.println("[Consultas] -- Creando Statement..");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			// Erantzunetik informazioa erauzi
			while (rs.next()) {
				// Bueltatu zutabeen izenen arabera
				int cod_est = rs.getInt("cod_est");
				String nombre = rs.getString("nombre");
				String pueblo = rs.getString("pueblo");
				String latitud = rs.getString("latitud");
				String longitud = rs.getString("longitud");
				String ICA_estacion = rs.getString("ICA_estacion");
				int cod_mun = rs.getInt("cod_mun");

				// Emaitzak pantailaratu
				Estazioa est = new Estazioa(cod_est, nombre, pueblo, latitud, longitud, ICA_estacion, cod_mun);
				arrayEstaciones.add(est);
			}

			// Garbiketa
			rs.close();
			stmt.close();
			MDK.deskonektatu();

			System.out.println("[Consultas] -- Resultado devuelto");
			System.out.println("[Consultas] -- Conexión con la base de datos cerrada");
		} catch (SQLException se) {
			// JDBC erroreak
			se.printStackTrace();
		} catch (Exception e) {
			// Class.forName errorea
			e.printStackTrace();
		} finally {
			// itxi erabilitako errekurtsoak
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return arrayEstaciones;

	}

	public static ArrayList<EspaciosNaturales> consultaEspaciosNaturales(String sql, String bbdd) {

		ArrayList<EspaciosNaturales> arrayEspaciosNaturales = new ArrayList<EspaciosNaturales>();

		Connection conn = null;
		Statement stmt = null;

		try {

			// Konexioa Ireki
			System.out.println("[Consultas] -- Conectando a la base de datos...");
			ConnectMariaDB MDK = ConnectMariaDB.getInstance();
			conn = MDK.konektatu(bbdd);

			// query edo SQL sententzia egikaritu
			System.out.println("[Consultas] -- Creando Statement..");
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			/*
			 	private int cod_esp_natural;
				private String nombre;
				private String tipo;
				private double longitud;
				private double latitud;
			 */

			// Erantzunetik informazioa erauzi
			while (rs.next()) {
				// Bueltatu zutabeen izenen arabera
				int cod_esp_natural = rs.getInt("cod_esp_natural");
				String nombre = rs.getString("nombre");
				String tipo = rs.getString("tipo");
				double longitud = rs.getDouble("longitud");
				double latitud = rs.getDouble("latitud");

				// Emaitzak pantailaratu
				EspaciosNaturales espNat = new EspaciosNaturales(cod_esp_natural, nombre, tipo, longitud, latitud);
				arrayEspaciosNaturales.add(espNat);
			}

			// Garbiketa
			rs.close();
			stmt.close();
			MDK.deskonektatu();

			System.out.println("[Consultas] -- Resultado devuelto");
			System.out.println("[Consultas] -- Conexión con la base de datos cerrada");
		} catch (SQLException se) {
			// JDBC erroreak
			se.printStackTrace();
		} catch (Exception e) {
			// Class.forName errorea
			e.printStackTrace();
		} finally {
			// itxi erabilitako errekurtsoak
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return arrayEspaciosNaturales;

	}

	
}
