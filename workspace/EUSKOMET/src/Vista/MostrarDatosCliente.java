package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.DatuKudeaketa.Cliente;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;
import Modelo.Hibernate.ObjectExtras.ToString;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class MostrarDatosCliente extends JFrame{
//public class MostrarDatosCliente extends JFrame implements ActionListener {


	private JPanel contentPane;
	private final static int PUERTO = 5000;
	//	private final String HOST = "127.0.0.1";
	//  private final static String HOST = "192.168.106.12";
	private final static String HOST = "localhost";

	Cliente cliente  = null;
	Socket socket = null;
	private String bbdd = "euskomet";

	private static JTextArea txtDatos;
	private JScrollPane scrollPane;
	@SuppressWarnings("rawtypes")
	private JComboBox cbProvincia;
	@SuppressWarnings("rawtypes")
	private JComboBox cbMunicipio;
	private JButton btnBuscar;

	Scanner sc = new Scanner(System.in); // abrir escaner

	ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
	ArrayList<Municipios> arrayMunicipios = new ArrayList<Municipios>();

	@SuppressWarnings("unused")
	private int cod_prov = 0;
	@SuppressWarnings("unused")
	private String datosStr = "";

	Socket clienteSckt = null;
	ObjectInputStream entrada = null;
	ObjectOutputStream salida = null;

	// --  Para tests --
	private boolean provTest = false;
	private boolean munTest = false;
	
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
	@SuppressWarnings("rawtypes")
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

	@SuppressWarnings("unchecked")
	public void ejecutarClienteProvincias(String bbdd) {
		try {
			//			clienteSckt = new Socket(HOST, PUERTO);
			System.out.println("-- Cli -- Prov -- Cliente iniciado");
			System.out.println("-- Cli -- Prov -- Conectando con el servidor...");
			System.out.println("-- Cli -- Prov -- Conexión realizada con servidor");

			System.out.println("-- Cli -- Prov -- Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("-- Cli -- Prov -- Nombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM Provincias";
			System.out.println("-- Cli -- Prov -- Enviando query...");
			salida.writeObject(sql);
			System.out.println("-- Cli -- Prov -- Query enviada [" + sql + "]");

			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
			System.out.println("-- Cli -- Prov -- arrayProvincias.size(): " + arrayProvincias.size());

			cbProvincia.addItem("");
			cbProvincia.setSelectedItem(0);
			String resultado = "";
			for (Provincias prov : arrayProvincias) {	
				resultado += ToString.toString(prov) + "\n";
//				cbProvincia.addItem(prov.getNombre());
			}

			System.out.println("-- Cli -- Prov -- Datos recibidos ---------------\n" + resultado + "-------------------------------------------------");
			provTest = true;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void ejecutarClienteMunicipios(String bbdd) {
		try {
			System.out.println("-- Cli -- Mun -- Cliente iniciado");
			System.out.println("-- Cli -- Mun -- Conectando con el servidor...");
			System.out.println("-- Cli -- Mun -- Conexión realizada con servidor");

			System.out.println("-- Cli -- Mun -- Enviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("-- Cli -- Mun -- Nombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM Municipios";
			System.out.println("-- Cli -- Mun -- Enviando query...");
			salida.writeObject(sql);
			System.out.println("-- Cli -- Mun -- Query enviada [" + sql + "]");

			arrayMunicipios = (ArrayList<Municipios>) entrada.readObject();
			System.out.println("-- Cli -- Prov -- arrayMunicipios.size(): " + arrayMunicipios.size());

			String resultado = "";
			for (Municipios mun : arrayMunicipios) {	
				resultado += ToString.toString(mun, false) + "\n";
			}

			System.out.println("-- Cli -- Mun -- Datos recibidos ---------------\n" + resultado + "-----------------------------------------------");
			munTest = true;
			salida.writeObject(false);
			mostrarTxt("TODOS LOS MUNICIPIOS: \n\n" + resultado);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void mostrarTxt (String texto) {
		txtDatos.setText(texto);
	}

	public JTextArea getTxtDatos() {
		return txtDatos;
	}

	@SuppressWarnings("static-access")
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
//				System.out.println("[btnBuscar]  --  (vacío)");
//				JOptionPane.showMessageDialog(contentPane, "Por favor, seleccione un municipio", "EUSKOMET: Información", JOptionPane.INFORMATION_MESSAGE);
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

	public boolean isProvTest() {
		return provTest;
	}

	public boolean isMunTest() {
		return munTest;
	}
	
	
	
}
