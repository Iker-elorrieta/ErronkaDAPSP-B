package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import Modelo.BBDD.Consultas;
import Modelo.Objetuak.Municipio;
import Modelo.Objetuak.Provincias;

public class Servidor extends Thread{
	private final static int PUERTO = 5000;

	private static Servidor serv = new Servidor();
	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;

	ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
	ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();

	private boolean testServidorBueno = false;

	public void run() {
		try {
			System.out.println("-- Serv -- Servidor iniciado");
			servidor = new ServerSocket(PUERTO);
			System.out.println("-- Serv --  ConsProv -- Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("-- Serv --  ConsProv -- Cliente conectado");

			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

				// --  Consulta Provincias
				System.out.println("-- Serv --  ConsProv -- Esperando a recibir nombre de la base de datos..."); 
				String bbdd = (String) entrada.readObject();
				System.out.println("-- Serv --  ConsProv -- [Nombre BBDD recibido: " + bbdd + "]");

				System.out.println("-- Serv --  ConsProv -- Esperando a recibir query..."); 
				String query = (String) entrada.readObject();
				System.out.println("-- Serv --  ConsProv -- [Query recibida: " + query + "]");

				if(query.contains("municipios")) {
					arrayMunicipios = Consultas.consultaMunicipios(query, bbdd);

					System.out.println("-- Serv --  ConsProv -- arrayProvincias.size(): " + arrayMunicipios.size());
					salida.writeObject(arrayMunicipios);
				}else if(query.contains("provincias")) {
					arrayProvincias = Consultas.consultaProvincias(query, bbdd);

					System.out.println("-- Serv --  ConsProv -- arrayProvincias.size(): " + arrayProvincias.size());
					salida.writeObject(arrayProvincias);
				}

				// --  Consulta Municipios
				System.out.println("-- Serv -- ConsMun -- Esperando a recibir nombre de la base de datos...");
				bbdd = (String) entrada.readObject();
				System.out.println("[Nombre BBDD recibido: " + bbdd + "]");

				System.out.println("-- Serv -- ConsMun -- Esperando a recibir query..."); 
				query = (String) entrada.readObject();
				System.out.println("-- Serv -- ConsMun -- [Query recibida: " + query + "]");

				if(query.contains("municipios")) {
					arrayMunicipios = Consultas.consultaMunicipios(query, bbdd);

					System.out.println("-- Serv -- ConsMun -- arrayProvincias.size(): " + arrayMunicipios.size());
					salida.writeObject(arrayMunicipios);
				}else if(query.contains("provincias")) {
					arrayProvincias = Consultas.consultaProvincias(query, bbdd);

					System.out.println("-- Serv -- ConsMun -- arrayProvincias.size(): " + arrayProvincias.size());
					salida.writeObject(arrayProvincias);
				}

				String continuar = (String) entrada.readObject();

			this.testServidorBueno = false;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
			try {
				if (servidor != null)
					servidor.close();
				if (cliente != null)
					cliente.close();
				if (entrada != null)
					entrada.close();
				if (salida != null)
					salida.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Fin servidor");
		}
	}


	public static void main(String[] args) {
		serv.start();
	}


	public boolean isTestServidorBueno() {
		return this.testServidorBueno;
	}

}

