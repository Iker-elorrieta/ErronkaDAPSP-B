package Controlador;

import java.io.*;
import java.net.*;

public class HiloServidor extends Thread {
	PrintWriter fsalida;
	Socket socket = null;

	public HiloServidor(Socket s) throws IOException {// CONSTRUCTOR
		socket = s;
		// se crean flujos de entrada y salida
		fsalida = new PrintWriter(socket.getOutputStream(), true);
	}

	public void run() {// tarea a realizar con el cliente
		String cadena = "";

		System.out.println("COMUNICO CON: " + socket.toString());

		fsalida.println("te mando este mensaje desde el servidor jejeje");

		fsalida.close();
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}// ..

