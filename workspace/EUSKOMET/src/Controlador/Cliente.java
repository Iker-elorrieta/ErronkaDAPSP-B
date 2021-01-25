package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.util.Scanner;


public class Cliente {

	private final static int PUERTO = 5000;
	private final static String HOST = "127.0.0.1";
	
	Scanner sc = new Scanner(System.in); // abrir escaner
	
	public static Socket cliente = null;
	public static ObjectOutputStream salida = null;
	public static ObjectInputStream entrada = null;
	
	
	public static void iniciarCliente() {
		
		try {
			System.out.println("[Client]\t--\tCliente iniciado");
			System.out.println("[Client]\t--\tConectando con el servidor...");
			cliente = new Socket(HOST, PUERTO);
			System.out.println("[Client]\t--\tConexión realizada con servidor");

			salida = new ObjectOutputStream (cliente.getOutputStream());
			entrada = new ObjectInputStream(cliente.getInputStream());			
			System.out.println("[Client]\t--\tI/O creado");

		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void desconectarCliente() {
		try {
			if (cliente != null)
				cliente.close();
			if (entrada != null)
				entrada.close();
			if (salida != null)
				salida.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[Client]\t--\tFin cliente");
	}
	
}
