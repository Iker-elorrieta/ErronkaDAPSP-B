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

	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;
	
	private boolean testServidorBueno = false;

	public void run() {
		try {
			System.out.println("Servidor iniciado");
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("Cliente conectado");

			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			System.out.println("Esperando a recibir nombre de la base de datos..."); 
			String bbdd = (String) entrada.readObject();
			System.out.println("[Nombre BBDD recibido: " + bbdd + "]");
			
			System.out.println("Esperando a recibir query..."); 
			String query = (String) entrada.readObject();
			System.out.println("[Query recibida: " + query + "]");
			
			if(query.contains("municipios")) {
				ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
				arrayMunicipios = Consultas.consultaMunicipios(query, bbdd);

				salida.writeObject(arrayMunicipios);
			}else if(query.contains("provincias")) {
				ArrayList<Provincias> arrayMunicipios = new ArrayList<Provincias>();
				arrayMunicipios = Consultas.consultaProvincias(query, bbdd);

				salida.writeObject(arrayMunicipios);
			}
			 
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
		Servidor serv = new Servidor();
		serv.start();
	}


	public boolean isTestServidorBueno() {
		return this.testServidorBueno;
	}


	
	

}

