package Controlador;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Servidor {
private final static int PUERTO = 5000;
	
public static int main(String args[]) throws IOException  {
	
	int estado = 1;
	ServerSocket servidor;
	
	servidor = new ServerSocket(PUERTO);
	System.out.println("Servidor iniciado...");
	
	while (true) {	
		Socket cliente = new Socket();
		cliente=servidor.accept();//esperando cliente	
		HiloServidor hilo = new HiloServidor(cliente);
		hilo.start();		
	}
}

}

