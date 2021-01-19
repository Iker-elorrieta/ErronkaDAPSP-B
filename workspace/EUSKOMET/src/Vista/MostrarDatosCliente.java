package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Modelo.Objetuak.Provincias;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

public class MostrarDatosCliente extends JFrame{
//public class MostrarDatosCliente extends JFrame implements ActionListener {


	private JPanel contentPane;
	private final static int PUERTO = 5000;
//	private final String HOST = "127.0.0.1";
	private final static String HOST = "localhost";
	
	Cliente cliente  = null;
	Socket socket = null;
	private String bbdd = "euskomet";

	private static JTextArea txtDatos;
	private JScrollPane scrollPane;
	private JComboBox cbProvincia;
	private JComboBox cbMunicipio;
	private JButton btnBuscar;
	
	Scanner sc = new Scanner(System.in); // abrir escaner
	
	ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
	ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();

	private int cod_prov = 0;
	private JButton btnConfirmarProv;
	
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
		setBounds(100, 100, 719, 400);
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
		
		cbProvincia = new JComboBox();
		cbProvincia.setBounds(495, 97, 198, 22);
//		cbProvincia.addActionListener(this);
		contentPane.add(cbProvincia);		
		
		JLabel lblProvincia = new JLabel("Escoja una provincia:");
		lblProvincia.setBounds(495, 72, 198, 14);
		contentPane.add(lblProvincia);
		
		JLabel lblMunicipio = new JLabel("Escoja un municipio");
		lblMunicipio.setBounds(495, 205, 198, 14);
		contentPane.add(lblMunicipio);
		
		cbMunicipio = new JComboBox();
		cbMunicipio.setBounds(495, 230, 198, 22);
		contentPane.add(cbMunicipio);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(495, 279, 198, 23);
		contentPane.add(btnBuscar);
		
		btnConfirmarProv = new JButton("Confirmar provincia");
		btnConfirmarProv.setBounds(495, 142, 198, 23);
		contentPane.add(btnConfirmarProv);

		cliente = new Cliente();
		
		ejecutarClienteProvincias(this.bbdd);
		
//		String cbProvinciaSI = (String) cbProvincia.getSelectedItem();
//		int cod_prov = conseguirCodProv();
		
//		ejecutarClienteMunicipios(this.bbdd, cod_prov);
	}

	public int ejecutarClienteProvincias(String bbdd) {
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
			
			System.out.println("Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("Nombre BBDD enviado [" + bbdd + "]");
			
//			String sql = "SELECT * FROM municipios";
//			System.out.println("Enviando query...");
//			salida.writeObject(sql);
//			System.out.println("Query enviada [" + sql + "]");
//
//			ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
//			arrayMunicipios = (ArrayList<Municipio>) entrada.readObject();
//			
//			String resultado = "";
//			for (Municipio mun : arrayMunicipios) {	
//				resultado += mun.toString() + "\n";
//				System.out.println(mun.toString());
//			}
			
			String sql = "SELECT * FROM provincias";
			System.out.println("Enviando query...");
			salida.writeObject(sql);
			System.out.println("Query enviada [" + sql + "]");

//			ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
			
			String resultado = "";
			for (Provincias prov : arrayProvincias) {	
				resultado += prov.toString() + "\n";
				cbProvincia.addItem(prov.getNombre());
				
				System.out.println(prov.toString());
			}
			
			
			System.out.println("1");
			
//			txtDatos.setText(resultado);
			mostrarTxt(resultado);
			System.out.println("2");
			resultadoTest = 1;
			System.out.println("3");

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
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
	
	public int conseguirCodProv() {
		int cod_prov = 0;
		if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Bizkaia")) {
			for (Provincias prov : arrayProvincias) {	
				if(prov.getNombre().equalsIgnoreCase("Bizkaia")) {
					cod_prov = prov.getCod_prov();
				}
				
			}
		}else if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Gipuzkoa")) {
			for (Provincias prov : arrayProvincias) {	
				if(prov.getNombre().equalsIgnoreCase("Gipuzkoa")) {
					cod_prov = prov.getCod_prov();
				}
				
			}
		}else if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Araba")) {
			for (Provincias prov : arrayProvincias) {	
				if(prov.getNombre().equalsIgnoreCase("Araba")) {
					cod_prov = prov.getCod_prov();
				}
				
			}
		}
		return cod_prov;
	}
	
	public int ejecutarClienteMunicipios(String bbdd, int cod_prov) {
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
			
			System.out.println("Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("Nombre BBDD enviado [" + bbdd + "]");
			
			String sql = "SELECT * FROM municipios WHERE cod_prov = " + cod_prov;
			System.out.println("Enviando query...");
			salida.writeObject(sql);
			System.out.println("Query enviada [" + sql + "]");

//			ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
			arrayMunicipios = (ArrayList<Municipio>) entrada.readObject();
			
			String resultado = "";
			for (Municipio mun : arrayMunicipios) {	
				resultado += mun.toString() + "\n";
				cbProvincia.addItem(mun.getNombre());				
				
				System.out.println(mun.toString());
			}
			
//			String sql = "SELECT * FROM provincias";
//			System.out.println("Enviando query...");
//			salida.writeObject(sql);
//			System.out.println("Query enviada [" + sql + "]");
//
//			ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
//			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
//			
//			String resultado = "";
//			for (Provincias prov : arrayProvincias) {	
//				resultado += prov.toString() + "\n";
//				cbProvincia.addItem(prov.getNombre());
//				
//				System.out.println(prov.toString());
//			}
			
			
			System.out.println("1");
			
//			txtDatos.setText(resultado);
			mostrarTxt(resultado);
			System.out.println("2");
			resultadoTest = 1;
			System.out.println("3");

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
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
	
//	public void actionPerformed(ActionEvent e) { 
//		if (e.getSource() == cbProvincia) {
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Bizkaia")) {
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Bizkaia")) {
//						cod_prov = prov.getCod_prov();
//					}
//					
//				}
//			}else if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Gipuzkoa")) {
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Gipuzkoa")) {
//						cod_prov = prov.getCod_prov();
//					}
//					
//				}
//			}else if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Araba")) {
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Araba")) {
//						cod_prov = prov.getCod_prov();
//					}
//					
//				}
//			}
//			ejecutarClienteMunicipios(this.bbdd, cod_prov);
//		}
//		
////		if (e.getSource() == cbProvincia) {
////			ejecutarClienteMunicipios(this.bbdd, cod_prov);
////		}
//		
//	}
}
