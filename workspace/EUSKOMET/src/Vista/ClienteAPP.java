package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Cliente;
import Controlador.Server;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class ClienteAPP extends JFrame {

	private JPanel contentPane;

	Cliente cliente  = null;
	Socket socket = null;
	private String bbdd = "euskomet";
	Socket clienteSckt = null;
	ObjectOutputStream salida = null;
	ObjectInputStream entrada = null;


	//	static Cliente cliente = new Cliente();

	static ArrayList<Provincias> arrayProvincias = new ArrayList<Provincias>();
	static ArrayList<Municipios> arrayMunicipios = new ArrayList<Municipios>();
	static ArrayList<EspaciosNaturales> arrayEspaciosNaturales = new ArrayList<EspaciosNaturales>();
	static ArrayList<Estaciones> arrayEstaciones = new ArrayList<Estaciones>();
	static ArrayList<MunEspNa> arrayEspNatMunicipios = new ArrayList<MunEspNa>();

	static menuPrincipal menuPrincipal;
	static mostrarMunicipios mostrarMunicipios;
	static mostrarEspaciosNaturales mostrarEspaciosNaturales;
	static mostrarEstaciones mostrarEstaciones;


	// --  Para tests --
	private boolean clienteTest = false;
	private boolean provinciasTest = false;
	private boolean municipiosTest = false;
	private boolean espaciosNaturalesTest = false;
	private boolean estacionesTest = false;
	private boolean espNatMunicipiosTest = false;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteAPP frame = new ClienteAPP();
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
	public ClienteAPP() {

		Cliente.iniciarCliente();

		salida = Cliente.salida;
		entrada = Cliente.entrada;

		obtenerDatos();

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 727, 490);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setResizable(false);
		setContentPane(contentPane);

		menuPrincipal = new menuPrincipal();
		//		menuPrincipal.setBounds(10, 11, 600, 450);
		menuPrincipal.setBounds(0, 0, 711, 395);
		menuPrincipal.setLayout(null);
		menuPrincipal.setVisible(true);


		mostrarMunicipios = new mostrarMunicipios();
		mostrarMunicipios.setBounds(0, 0, 711, 395);
		mostrarMunicipios.setLayout(null);
		mostrarMunicipios.setVisible(false);

		mostrarEspaciosNaturales = new mostrarEspaciosNaturales();
		mostrarEspaciosNaturales.setBounds(0, 0, 711, 395);
		mostrarEspaciosNaturales.setLayout(null);
		mostrarEspaciosNaturales.setVisible(false);

		mostrarEstaciones = new mostrarEstaciones();
		mostrarEstaciones.setBounds(0, 0, 711, 395);
		mostrarEstaciones.setLayout(null);
		mostrarEstaciones.setVisible(false);

		getContentPane().add(menuPrincipal);
		getContentPane().add(mostrarMunicipios);
		getContentPane().add(mostrarEspaciosNaturales);
		getContentPane().add(mostrarEstaciones);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente.desconectarCliente();
				//				Server.setContinuar(false);
				System.exit(0);
			}
		});
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(556, 394, 134, 44);
		contentPane.add(btnNewButton);
		
		JLabel lblEuskomet = new JLabel("EUSKOMET");
		lblEuskomet.setForeground(new Color(0, 128, 0));
		lblEuskomet.setFont(new Font("Tahoma", Font.PLAIN, 40));
		lblEuskomet.setBounds(10, 385, 214, 53);
		contentPane.add(lblEuskomet);
		

//		JButton btnActualizarDatos = new JButton("ACTUALIZAR DATOS");
//		btnActualizarDatos.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//hacer clear de los arraylist
//				actualizarDatos();
//			}
//		});
//		btnActualizarDatos.setForeground(Color.WHITE);
//		btnActualizarDatos.setBackground(Color.BLUE);
//		btnActualizarDatos.setBounds(425, 397, 177, 23);
//		contentPane.add(btnActualizarDatos);

		clienteTest = true;

	}

	public static ArrayList<Provincias> getArrayProvincias() {
		return arrayProvincias;
	}

	public static void setArrayProvincias(ArrayList<Provincias> newArrayProvincias) {
		arrayProvincias = newArrayProvincias;
	}

	public static ArrayList<Municipios> getArrayMunicipios() {
		return arrayMunicipios;
	}

	public static void setArrayMunicipios(ArrayList<Municipios> newArrayMunicipios) {
		arrayMunicipios = newArrayMunicipios;
	}

	public static ArrayList<EspaciosNaturales> getArrayEspaciosNaturales() {
		return arrayEspaciosNaturales;
	}

	public static void setArrayEspaciosNaturales(ArrayList<EspaciosNaturales> newArrayEspaciosNaturales) {
		arrayEspaciosNaturales = newArrayEspaciosNaturales;
	}

	public static ArrayList<MunEspNa> getArrayEspNatMunicipios() {
		return arrayEspNatMunicipios;
	}

	public static void setArrayEspNatMunicipios(ArrayList<MunEspNa> arrayEspNatMunicipios) {
		ClienteAPP.arrayEspNatMunicipios = arrayEspNatMunicipios;
	}

	public static ArrayList<Estaciones> getArrayEstaciones() {
		return arrayEstaciones;
	}

	public static void setArrayEstaciones(ArrayList<Estaciones> arrayEstaciones) {
		ClienteAPP.arrayEstaciones = arrayEstaciones;
	}

	public void ejecutarClienteProvincias(String bbdd) {
		try {

			System.out.println("[Provincias]\t--\tProv\t--\tEnviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("[Provincias]\t--\tProv\t--\tNombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM Provincias";
			//			String sql = "SELECT * FROM provincias";
			System.out.println("[Provincias]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(sql);
			System.out.println("[Provincias]\t--\tProv\t--\tQuery enviada [" + sql + "]");

			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
			System.out.println("[EspNat]\t--\tProv\t--\tarrayProvincias.size(): " + arrayProvincias.size());

			provinciasTest = true;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEspaciosNaturales(String bbdd) {
		try {

			System.out.println("[EspNat]\t--\tEnviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("[EspNat]\t--\tNombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM EspaciosNaturales";
			//			String sql = "SELECT * FROM espacios_naturales";
			System.out.println("[EspNat]\t--\tEnviando query...");
			salida.writeObject(sql);
			System.out.println("[EspNat]\t--\tQuery enviada [" + sql + "]");

			arrayEspaciosNaturales = (ArrayList<EspaciosNaturales>) entrada.readObject();
			System.out.println("[EspNat]\t--\tarrayEspaciosNaturales.size(): " + arrayEspaciosNaturales.size());

			salida.writeObject(true); //continuar con el do while del servidor
			espaciosNaturalesTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteMunicipios(String bbdd) {
		try {

			System.out.println("[Municipios]\t--\tMun\t--\tEnviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("[Municipios]\t--\tMun\t--\tNombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM Municipios";
			//			String sql = "SELECT * FROM municipios";
			System.out.println("[Municipios]\t--\tMun\t--\tEnviando query...");
			salida.writeObject(sql);
			System.out.println("[Municipios]\t--\tMun\t--\tQuery enviada [" + sql + "]");

			arrayMunicipios = (ArrayList<Municipios>) entrada.readObject();
			System.out.println("[Municipios]\t--\tMun\t--\tarrayMunicipios.size(): " + arrayMunicipios.size());

			municipiosTest = true;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEspNatMunicipios(String bbdd) {
		try {

			System.out.println("[EspNat_Mun]\t--\tProv\t--\tEnviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("[EspNat_Mun]\t--\tProv\t--\tNombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM MunEspNa";
			//			String sql = "SELECT * FROM mun_esp_na";
			System.out.println("[EspNat_Mun]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(sql);
			System.out.println("[EspNat_Mun]\t--\tProv\t--\tQuery enviada [" + sql + "]");

			arrayEspNatMunicipios = (ArrayList<MunEspNa>) entrada.readObject();
			System.out.println("[EspNat_Mun]\t--\tProv\t--\tarrayEspNatMunicipios.size(): " + arrayEspNatMunicipios.size());


			espNatMunicipiosTest = true;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEstaciones(String bbdd) {
		try {

			System.out.println("[Estaciones]\t--\tProv\t--\tEnviando nombre de la BBDD...");
			salida.writeObject(bbdd);
			System.out.println("[Estaciones]\t--\tProv\t--\tNombre BBDD enviado [" + bbdd + "]");

			String sql = "FROM Estaciones";
			//			String sql = "SELECT * FROM estaciones";
			System.out.println("[Estaciones]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(sql);
			System.out.println("[Estaciones]\t--\tProv\t--\tQuery enviada [" + sql + "]");

			arrayEstaciones = (ArrayList<Estaciones>) entrada.readObject();
			System.out.println("[Estaciones]\t--\tProv\t--\tarrayEspNatMunicipios.size(): " + arrayEstaciones.size());


			estacionesTest = true;
			salida.writeObject(true);

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	private void obtenerDatos() {
		ejecutarClienteProvincias(this.bbdd);
		ejecutarClienteMunicipios(this.bbdd);
		ejecutarClienteEstaciones(this.bbdd);
		ejecutarClienteEspaciosNaturales(this.bbdd);
		ejecutarClienteEspNatMunicipios(this.bbdd);
		
	}
	
	private void actualizarDatos() {
		ejecutarClienteProvincias(this.bbdd);
		ejecutarClienteMunicipios(this.bbdd);
		ejecutarClienteEstaciones(this.bbdd);
		ejecutarClienteEspaciosNaturales(this.bbdd);
		ejecutarClienteEspNatMunicipios(this.bbdd);
		
		mostrarMunicipios.setArrayProvincias(null);
		mostrarMunicipios.setArrayMunicipios(null);
		mostrarMunicipios.setArrayProvincias(arrayProvincias);
		mostrarMunicipios.setArrayMunicipios(arrayMunicipios);
		
		mostrarEspaciosNaturales.setArrayProvincias(null);
		mostrarEspaciosNaturales.setArrayEspNatMunicipios(null);
		mostrarEspaciosNaturales.setArrayEspaciosNaturales(null);
		mostrarEspaciosNaturales.setArrayEspNatMunicipios(null);
		mostrarEspaciosNaturales.setArrayProvincias(arrayProvincias);
		mostrarEspaciosNaturales.setArrayEspNatMunicipios(arrayEspNatMunicipios);
		mostrarEspaciosNaturales.setArrayEspaciosNaturales(arrayEspaciosNaturales);
		mostrarEspaciosNaturales.setArrayEspNatMunicipios(arrayEspNatMunicipios);
		
		mostrarEstaciones.setArrayProvincias(null);
		mostrarEstaciones.setArrayMunicipios(null);
		mostrarEstaciones.setArrayEstaciones(null);
		mostrarEstaciones.setArrayProvincias(arrayProvincias);
		mostrarEstaciones.setArrayMunicipios(arrayMunicipios);
		mostrarEstaciones.setArrayEstaciones(arrayEstaciones);
	}

	public boolean isProvinciasTest() {
		return provinciasTest;
	}

	public boolean isMunicipiosTest() {
		return municipiosTest;
	}

	public boolean isEspaciosNaturalesTest() {
		return espaciosNaturalesTest;
	}

	public boolean isEstacionesTest() {
		return estacionesTest;
	}

	public boolean isEspNatMunicipiosTest() {
		return espNatMunicipiosTest;
	}

	public boolean isClienteTest() {
		return clienteTest;
	}
}