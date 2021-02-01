package Controlador;

import java.awt.EventQueue;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Vista.Launcher;

//import Vista.VentanaServidor;

public class Server extends Thread{

	private final static int PUERTO = 5000;
	ServerSocket servidor = null;
	Socket cliente;

	private static int idCliente = 0;
	private static int cantClientes = 0;

	private boolean testServidorBueno = false;

	public static void main(String[] args) {
		Server serv = new Server();
		serv.start();
	}

	public void run() {
		testServidorBueno = false;
		
		

		System.out.println("[Server]\t--\tIniciando servidor...");
		try {
			servidor = new ServerSocket(PUERTO);
			System.out.println("[Server]\t--\tServidor iniciado");
			
//			System.out.println("[Server]\t--\tActualizando datos...");
//			Launcher.ejecutar();
//			System.out.println("[Server]\t--\tDatos actualizados");
			
			
			System.out.println("[Server]\t--\tEsperando conexiones del cliente...");

			while(cantClientes < 6) {	
				idCliente += 1;
				cantClientes += 1;
				cliente = new Socket();
				try {
					cliente=servidor.accept();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//esperando cliente
				System.out.println("[Server]\t--\tCliente conectado [id=" + idCliente + ", " + cliente.toString() + "]");
				System.out.println("[Server]\t--\tIniciando cliente... [id=" + idCliente + ", " + cliente.toString() + "]");
				HiloServer hilo = null;
				try {
					hilo = new HiloServer(cliente, idCliente);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				hilo.start();		
				System.out.println("[Server]\t--\tCliente iniciado [id=" + idCliente + ", " + cliente.toString() + "]");
				System.out.println("[Server]\t--\tClientes en línea:\t" + cantClientes);
			}

			testServidorBueno = true;
			System.out.println("[Server]\t--\tEl servidor se ha cerrado");
		} catch (IOException e) {
			System.err.println("[Server]\t--\tEl servidor ya está en ejecución");
		}


	}

	public boolean isTestServidorBueno() {
		return testServidorBueno;
	}

	public static void restaCantClientes(int cant) {
		Server.cantClientes -= cant;
	}



}
