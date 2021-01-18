package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.DatuKudeaketa.Cliente;
import Modelo.Objetuak.Municipio;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MostrarDatosCliente extends JFrame {

	private JPanel contentPane;
	private final static int PUERTO = 5000;
//	private final String HOST = "127.0.0.1";
	private final static String HOST = "localhost";
	
	Cliente cliente  = null;
	Socket socket = null;

	private static JTextArea txtDatos;
	private JScrollPane scrollPane;
	
	Scanner sc = new Scanner(System.in); // abrir escaner

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarDatosCliente frame = new MostrarDatosCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MostrarDatosCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtDatos = new JTextArea();
		txtDatos.setBounds(10, 11, 464, 339);
		contentPane.add(txtDatos);

		scrollPane = new JScrollPane(txtDatos);
		scrollPane.setBounds(10, 11, 464, 339);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		contentPane.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		cliente = new Cliente();
		
		ejecutarCliente();
	}

	public int ejecutarCliente() {
		int resultadoTest = 0;
		Socket cliente = null;
		ObjectInputStream entrada = null;
		ObjectOutputStream salida = null;
		
		try {
			cliente = new Socket(HOST, PUERTO);
			System.out.println("Cliente iniciado");
			System.out.println("Conectando con el servidor...");
			System.out.println("Conexión realizada con servidor");

			entrada = new ObjectInputStream(cliente.getInputStream());
			salida = new ObjectOutputStream (cliente.getOutputStream());
			
			String sql = "SELECT * FROM municipios";
			System.out.println("Enviando query...");
			salida.writeObject(sql);
			System.out.println("Query enviada [" + sql + "]");

			ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
			arrayMunicipios = (ArrayList<Municipio>) entrada.readObject();
			
			String resultado = "";
			for (Municipio mun : arrayMunicipios) {	
				resultado += mun.toString() + "\n";
				System.out.println(mun.toString());
			}
			System.out.println("1");
			
//			txtDatos.setText(resultado);
			mostrarTxt(resultado);
			System.out.println("2");
			resultadoTest = 1;
			System.out.println("3");

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		} finally {
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
			System.out.println("Fin cliente");
		}
		return resultadoTest;
	}//
	
	public static void mostrarTxt (String texto) {
		txtDatos.setText(texto);
	}

	public JTextArea getTxtDatos() {
		return txtDatos;
	}

	public void setTxtDatos(JTextArea txtDatos) {
		this.txtDatos = txtDatos;
	}
}
