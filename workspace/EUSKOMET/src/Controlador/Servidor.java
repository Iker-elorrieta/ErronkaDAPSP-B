package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Modelo.Hibernate.Select;
import Vista.Launcher;

public class Servidor extends Thread{
	private final static int PUERTO = 5000;

	private static Servidor serv = new Servidor();
	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;

//	ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
//	ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();

	private boolean testServidorBueno = false;
	private boolean continuar = false;

	public void run() {
		Launcher.ejecutar();
		try {
			System.out.println("-- Serv -- Servidor iniciado");
			servidor = new ServerSocket(PUERTO);
			System.out.println("-- Serv --  ConsProv -- Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("-- Serv --  ConsProv -- Cliente conectado");

			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			do {
				// --  Consultas -- Según la query se hace de municipios o de provincias
				System.out.println("-- Serv --  ConsProv -- Esperando a recibir nombre de la base de datos..."); 
				String bbdd = (String) entrada.readObject();
				System.out.println("-- Serv --  ConsProv -- [Nombre BBDD recibido: " + bbdd + "]");

				System.out.println("-- Serv --  ConsProv -- Esperando a recibir query..."); 
				String query = (String) entrada.readObject();
				System.out.println("-- Serv --  ConsProv -- [Query recibida: " + query + "]");

//				arrayMunicipios = Consultas.consultaMunicipios(query, bbdd);
					
				ArrayList<Object> array = Select.selectQuery(query);

//				System.out.println("-- Serv --  ConsProv -- arrayProvincias.size(): " + arrayMunicipios.size());
				System.out.println("-- Serv --  ConsProv -- arrayProvincias.size(): " + array.size());
//				salida.writeObject(arrayMunicipios);

				salida.writeObject(array);
				continuar = (boolean) entrada.readObject();
			}while (continuar);

			this.testServidorBueno = true;
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

