package Controlador;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import Modelo.Hibernate.HibernateUtil;
//import Modelo.BBDD.Consultas;
import Modelo.Hibernate.Select;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;

public class HiloServer extends Thread {
	private static ObjectInputStream entrada = null;
	private static ObjectOutputStream salida = null;
	Socket cliente = null;
	private static int idCliente = 0;

	Server serv = new Server();

	ArrayList<Municipios> arrayMunicipios = new ArrayList<Municipios>();
	ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
	ArrayList<EspaciosNaturales> arrayEspaciosNaturales = new ArrayList<EspaciosNaturales>();
	ArrayList<Estaciones> arrayEstaciones = new ArrayList<Estaciones>();
	ArrayList<MunEspNa> arrayEspNatMunicipios = new ArrayList<MunEspNa>();

	private boolean testServidorBueno = false;
	private boolean continuar = false;

	public HiloServer(Socket cl, int idCliente) throws IOException {// CONSTRUCTOR
		cliente = cl;
		this.idCliente = idCliente;
		// se crean flujos de entrada y salida
		salida = new ObjectOutputStream (cliente.getOutputStream());
		entrada = new ObjectInputStream(cliente.getInputStream());
	}

	public void run() {// tarea a realizar con el cliente
		String bbdd = "";
		String query = "";

		try {
			int i = 0;
			do {
				// --  Consultas -- 
				System.out.println("[HiloId:" +idCliente+ "]\t--\tEsperando a recibir query...");
				query = (String) entrada.readObject();
				System.out.println("[HiloId:" +idCliente+ "]\t--\t[Query recibida: " + query + "]");

				ArrayList<Object> array = Select.selectQuery(query);
				System.out.println("[HiloId:" +idCliente+ "]\t--\tarray.size(): " + array.size());
				salida.writeObject(array);
				i++;
			}while (i < 5);

			Cliente.desconectarCliente();

			this.testServidorBueno = true;
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}

		Server.restaCantClientes(1);
		System.out.println("[HiloId:" +idCliente+ "]\t--\tFin de comunicación con " + cliente.toString());
	}

	public boolean isContinuar() {
		return continuar;
	}

	public void setContinuar(boolean continuar) {
		this.continuar = continuar;
	}





}// ..

