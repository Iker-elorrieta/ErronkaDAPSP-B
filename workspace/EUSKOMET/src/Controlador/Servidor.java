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

public class Servidor {
	private final static int PUERTO = 5000;

	private static ServerSocket servidor = null;
	private static Socket cliente = null;
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;

	public static int iniciar() {
		int resultadoTest = 0;
		try {
			System.out.println("Servidor iniciado");
			servidor = new ServerSocket(PUERTO);
			System.out.println("Esperando conexiones del cliente...");
			cliente = servidor.accept();
			System.out.println("Cliente conectado");

			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());

			System.out.println("Esperando a recibir query..."); 
			String mensaje = (String) entrada.readObject();
			System.out.println("[Mensaje recibido: " + mensaje + "]");

			ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
			arrayMunicipios = Consultas.consultaMunicipios(mensaje);

			salida.writeObject(arrayMunicipios); 
			resultadoTest = 1;

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
		return resultadoTest;
	}


	public static void main(String[] args) {
		Servidor s1 = new Servidor();
		s1.iniciar();
	}

}

