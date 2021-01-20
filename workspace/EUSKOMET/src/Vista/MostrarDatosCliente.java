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
import javax.swing.JOptionPane;
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
	private String datosStr = "";

	Socket clienteSckt = null;
	ObjectInputStream entrada = null;
	ObjectOutputStream salida = null;

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
		lblMunicipio.setBounds(495, 130, 198, 14);
		contentPane.add(lblMunicipio);

		cbMunicipio = new JComboBox();
		cbMunicipio.setBounds(495, 155, 198, 22);
		contentPane.add(cbMunicipio);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(495, 204, 198, 23);
//		btnBuscar.addActionListener(this);
		contentPane.add(btnBuscar);

		cliente = new Cliente();
		abrirES();

		ejecutarClienteProvincias(this.bbdd);
		ejecutarClienteMunicipios(this.bbdd);

		cerrarCliente();
	}

	public void abrirES () {
		try {
			clienteSckt = new Socket(HOST, PUERTO);
		} catch (IOException e) {
			System.err.println("Error al iniciar el socket ClienteSckt");
		}

		try {
			entrada = new ObjectInputStream(clienteSckt.getInputStream());
			salida = new ObjectOutputStream (clienteSckt.getOutputStream());
		} catch (IOException e) {
			System.err.println("Error al iniciar el I/O Stream");
		}
	}

	public void cerrarCliente () {
		try {
			if (clienteSckt != null)
				clienteSckt.close();
			if (entrada != null)
				entrada.close();
			if (salida != null)
				salida.close();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		System.out.println("-- Cli -- Fin cliente");
	}

	public int ejecutarClienteProvincias(String bbdd) {
		int resultadoTest = 0;
		try {
			//			clienteSckt = new Socket(HOST, PUERTO);
			System.out.println("-- Cli -- Prov -- Cliente iniciado");
			System.out.println("-- Cli -- Prov -- Conectando con el servidor...");
			System.out.println("-- Cli -- Prov -- Conexi�n realizada con servidor");

			System.out.println("-- Cli -- Prov -- Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("-- Cli -- Prov -- Nombre BBDD enviado [" + bbdd + "]");

			String sql = "SELECT * FROM provincias";
			System.out.println("-- Cli -- Prov -- Enviando query...");
			salida.writeObject(sql);
			System.out.println("-- Cli -- Prov -- Query enviada [" + sql + "]");

			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
			System.out.println("-- Cli -- Prov -- arrayProvincias.size(): " + arrayProvincias.size());

			cbProvincia.addItem("");
			cbProvincia.setSelectedItem(0);
			String resultado = "";
			for (Provincias prov : arrayProvincias) {	
				resultado += prov.toString() + "\n";
				cbProvincia.addItem(prov.getNombre());
			}

			System.out.println("-- Cli -- Prov -- Datos recibidos ---------------\n" + resultado + "-------------------------------------------------");
			resultadoTest = 1;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
		return resultadoTest;
	}//

	public int ejecutarClienteMunicipios(String bbdd) {
		int resultadoTest = 0;

		try {
			System.out.println("-- Cli -- Mun -- Cliente iniciado");
			System.out.println("-- Cli -- Mun -- Conectando con el servidor...");
			System.out.println("-- Cli -- Mun -- Conexi�n realizada con servidor");

			System.out.println("-- Cli -- Mun -- Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("-- Cli -- Mun -- Nombre BBDD enviado [" + bbdd + "]");

			String sql = "SELECT * FROM municipios";
			System.out.println("-- Cli -- Mun -- Enviando query...");
			salida.writeObject(sql);
			System.out.println("-- Cli -- Mun -- Query enviada [" + sql + "]");

			arrayMunicipios = (ArrayList<Municipio>) entrada.readObject();
			System.out.println("-- Cli -- Prov -- arrayMunicipios.size(): " + arrayMunicipios.size());

			String resultado = "";
			for (Municipio mun : arrayMunicipios) {	
				resultado += mun.toString() + "\n";
			}

			System.out.println("-- Cli -- Mun -- Datos recibidos ---------------\n" + resultado + "-----------------------------------------------");
			resultadoTest = 1;
			salida.writeObject(false);
			mostrarTxt("Municipios:\n" + resultado);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
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
//			System.out.println(" -- Has seleccionado " + cbProvincia.getSelectedItem() + "  --");
//			datosStr = "";
//			cbMunicipio.removeAllItems();
//
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("")) { 	
//
//				System.out.println("-- Cli -- cbBizkaia -- Datos devueltos ---------------\nNo hay datos\n-----------------------------------------------");
//
//			}
//
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Bizkaia")) { 	
//				cbMunicipio.addItem("");
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Bizkaia")) {
//						cod_prov = prov.getCod_prov();									
//					}
//				}									
//
//				for (Municipio mun : arrayMunicipios) {							
//					if(mun.getCod_prov() == this.cod_prov) {
//						cbMunicipio.addItem(mun.getNombre());
//					}
//				}
//			}
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Gipuzkoa")) { 	
//				cbMunicipio.addItem("");
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Gipuzkoa")) {
//						cod_prov = prov.getCod_prov();									
//					}
//				}									
//
//				for (Municipio mun : arrayMunicipios) {							
//					if(mun.getCod_prov() == this.cod_prov) {
//						cbMunicipio.addItem(mun.getNombre());
//					}
//				}
//
//			}
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Araba")) { 	
//				cbMunicipio.addItem("");
//				for (Provincias prov : arrayProvincias) {	
//					if(prov.getNombre().equalsIgnoreCase("Araba")) {
//						cod_prov = prov.getCod_prov();									
//					}
//				}									
//
//				for (Municipio mun : arrayMunicipios) {							
//					if(mun.getCod_prov() == this.cod_prov) {
//						cbMunicipio.addItem(mun.getNombre());
//					}
//				}
//			}
//		}
//
//		if (e.getSource() == btnBuscar) {
//			if(cbMunicipio.getSelectedIndex() == 0 || cbProvincia.getSelectedIndex() == 0) {
//				mostrarTxt("");
//				System.out.println("[btnBuscar]  --  (vac�o)");
//				JOptionPane.showMessageDialog(contentPane, "Por favor, seleccione un municipio", "EUSKOMET: Informaci�n", JOptionPane.INFORMATION_MESSAGE);
//			}else {
//				for (Municipio mun : arrayMunicipios) {							
//					if(mun.getNombre() == (String) cbMunicipio.getSelectedItem()) {
//						mostrarTxt("Municipio seleccionado:\n" + mun.toString());
//						System.out.println("[btnBuscar]  --  Municipio seleccionado:\n" + mun.toString());
//					}
//				}
//			}
//
//		}
//
//	}
}
