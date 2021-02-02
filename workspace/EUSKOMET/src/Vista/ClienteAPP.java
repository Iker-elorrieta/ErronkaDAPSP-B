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
import Modelo.Hibernate.Object.FavoritosMun;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;
import Modelo.Hibernate.ObjectExtras.ToString;

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

	Cliente cliente  = new Cliente();
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
	static ArrayList<FavoritosMun> arrayFavoritosMunBizkaia = new ArrayList<FavoritosMun>();
	static ArrayList<FavoritosMun> arrayFavoritosMunGipuzkoa = new ArrayList<FavoritosMun>();
	static ArrayList<FavoritosMun> arrayFavoritosMunAraba = new ArrayList<FavoritosMun>();

	static menuPrincipal menuPrincipal;
	static mostrarMunicipios mostrarMunicipios;
	static mostrarEspaciosNaturales mostrarEspaciosNaturales;
	static mostrarEstaciones mostrarEstaciones;
	static mostrarPlayas mostrarPlayas;
	static mostrarTopMunicipios mostrarTopMunicipios;


	// --  Para tests --
	private boolean clienteTest = false;
	private boolean provinciasTest = false;
	private boolean municipiosTest = false;
	private boolean espaciosNaturalesTest = false;
	private boolean estacionesTest = false;
	private boolean espNatMunicipiosTest = false;
	private boolean favoritosMunBizkaiaTest = false;
	private boolean favoritosMunGipuzkoaTest = false;
	private boolean favoritosMunArabaTest = false;

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

		cliente.iniciarCliente();

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
		
		mostrarPlayas = new mostrarPlayas();
		mostrarPlayas.setBounds(0, 0, 711, 395);
		mostrarPlayas.setLayout(null);
		mostrarPlayas.setVisible(false);
		
		mostrarTopMunicipios = new mostrarTopMunicipios();
		mostrarTopMunicipios.setBounds(0, 0, 711, 395);
		mostrarTopMunicipios.setLayout(null);
		mostrarTopMunicipios.setVisible(false);

		getContentPane().add(menuPrincipal);
		getContentPane().add(mostrarMunicipios);
		getContentPane().add(mostrarEspaciosNaturales);
		getContentPane().add(mostrarEstaciones);
		getContentPane().add(mostrarPlayas);
		getContentPane().add(mostrarTopMunicipios);

		JButton btnNewButton = new JButton("SALIR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente.desconectarCliente();
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
		
		cliente.desconectarCliente();

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

	public static ArrayList<Municipios> getArrayMunicipios() {
		return arrayMunicipios;
	}

	public static ArrayList<EspaciosNaturales> getArrayEspaciosNaturales() {
		return arrayEspaciosNaturales;
	}

	public static ArrayList<MunEspNa> getArrayEspNatMunicipios() {
		return arrayEspNatMunicipios;
	}

	public static ArrayList<Estaciones> getArrayEstaciones() {
		return arrayEstaciones;
	}

	public static ArrayList<FavoritosMun> getArrayFavoritosMunBizkaia() {
		return arrayFavoritosMunBizkaia;
	}
	
	public static ArrayList<FavoritosMun> getArrayFavoritosMunGipuzkoa() {
		return arrayFavoritosMunGipuzkoa;
	}

	public static ArrayList<FavoritosMun> getArrayFavoritosMunAraba() {
		return arrayFavoritosMunAraba;
	}

	public void ejecutarClienteProvincias() {
		try {

			String hql = "FROM Provincias";
			System.out.println("[Provincias]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[Provincias]\t--\tProv\t--\tQuery enviada [" + hql + "]");

			arrayProvincias = (ArrayList<Provincias>) entrada.readObject();
			System.out.println("[Provincias]\t--\t--\tarrayProvincias.size(): " + arrayProvincias.size());

			provinciasTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEspaciosNaturales() {
		try {

			String hql = "FROM EspaciosNaturales";
			System.out.println("[EspNat]\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[EspNat]\t--\tQuery enviada [" + hql + "]");

			arrayEspaciosNaturales = (ArrayList<EspaciosNaturales>) entrada.readObject();
			System.out.println("[EspNat]\t--\tarrayEspaciosNaturales.size(): " + arrayEspaciosNaturales.size());

			espaciosNaturalesTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteMunicipios() {
		try {

			String hql = "FROM Municipios";
			System.out.println("[Municipios]\t--\tMun\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[Municipios]\t--\tMun\t--\tQuery enviada [" + hql + "]");

			arrayMunicipios = (ArrayList<Municipios>) entrada.readObject();
			System.out.println("[Municipios]\t--\t--\tarrayMunicipios.size(): " + arrayMunicipios.size());

			municipiosTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEspNatMunicipios() {
		try {

			String hql = "FROM MunEspNa";
			System.out.println("[EspNat_Mun]\t--\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[EspNat_Mun]\t--\t--\tQuery enviada [" + hql + "]");

			arrayEspNatMunicipios = (ArrayList<MunEspNa>) entrada.readObject();
			System.out.println("[EspNat_Mun]\t--\t--\tarrayEspNatMunicipios.size(): " + arrayEspNatMunicipios.size());

			espNatMunicipiosTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteEstaciones() {
		try {

			String hql = "FROM Estaciones";
			System.out.println("[Estaciones]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[Estaciones]\t--\tProv\t--\tQuery enviada [" + hql + "]");

			arrayEstaciones = (ArrayList<Estaciones>) entrada.readObject();
			System.out.println("[Estaciones]\t--\tProv\t--\tarrayEspNatMunicipios.size(): " + arrayEstaciones.size());


			estacionesTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//

	public void ejecutarClienteTopMunicipiosBizkaia() {
		try {

//			String hql = "FROM Municipios where codMun in (SELECT municipios.codMun FROM FavoritosMun WHERE municipios.provincias.codProv = 48 GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC)";
			String hql = "FROM FavoritosMun WHERE municipios.provincias.nombre = 'Bizkaia' GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC";
			System.out.println("[FavoritosMun]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[FavoritosMun]\t--\tProv\t--\tQuery enviada [" + hql + "]");

			arrayFavoritosMunBizkaia = (ArrayList<FavoritosMun>) entrada.readObject();
			System.out.println("[FavoritosMun]\t--\t--\tarrayFavoritosMunBizkaia.size(): " + arrayFavoritosMunBizkaia.size());
			
			for (FavoritosMun mun :  arrayFavoritosMunBizkaia) {
				System.out.println("arrayFavoritosMunBizkaia -- " + ToString.toString(mun));
			}

			favoritosMunBizkaiaTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//
	
	public void ejecutarClienteTopMunicipiosGipuzkoa() {
		try {

//			String hql = "FROM Municipios where codMun in (SELECT municipios.codMun FROM FavoritosMun WHERE municipios.provincias.codProv = 48 GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC)";
			String hql = "FROM FavoritosMun WHERE municipios.provincias.nombre = 'Gipuzkoa' GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC";
			System.out.println("[FavoritosMun]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[FavoritosMun]\t--\tProv\t--\tQuery enviada [" + hql + "]");

			arrayFavoritosMunGipuzkoa = (ArrayList<FavoritosMun>) entrada.readObject();
			System.out.println("[FavoritosMun]\t--\t--\tarrayFavoritosMunGipuzkoa.size(): " + arrayFavoritosMunGipuzkoa.size());
			
			for (FavoritosMun mun :  arrayFavoritosMunGipuzkoa) {
				System.out.println("arrayFavoritosMunGipuzkoa -- " + ToString.toString(mun));
			}

			favoritosMunGipuzkoaTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//
	
	public void ejecutarClienteTopMunicipiosAraba() {
		try {

//			String hql = "FROM Municipios where codMun in (SELECT municipios.codMun FROM FavoritosMun WHERE municipios.provincias.codProv = 48 GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC)";
			String hql = "FROM FavoritosMun WHERE municipios.provincias.nombre = 'Araba' GROUP BY municipios.codMun ORDER BY COUNT(municipios.codMun) DESC";
			System.out.println("[FavoritosMun]\t--\tProv\t--\tEnviando query...");
			salida.writeObject(hql);
			System.out.println("[FavoritosMun]\t--\tProv\t--\tQuery enviada [" + hql + "]");

			arrayFavoritosMunAraba = (ArrayList<FavoritosMun>) entrada.readObject();
			System.out.println("[FavoritosMun]\t--\t--\tarrayFavoritosMunAraba.size(): " + arrayFavoritosMunAraba.size());
			
			for (FavoritosMun mun :  arrayFavoritosMunAraba) {
				System.out.println("arrayFavoritosMunAraba -- " + ToString.toString(mun));
			}

			favoritosMunArabaTest = true;

		} catch (IOException e) {
			System.out.println("Error (IOException): " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}//
	
	private void obtenerDatos() {
		ejecutarClienteProvincias();
		ejecutarClienteMunicipios();
		ejecutarClienteEstaciones();
		ejecutarClienteEspaciosNaturales();
		ejecutarClienteEspNatMunicipios();
		ejecutarClienteTopMunicipiosBizkaia();
		ejecutarClienteTopMunicipiosGipuzkoa();
		ejecutarClienteTopMunicipiosAraba();
	}
	
//	private void actualizarDatos() {
//		ejecutarClienteProvincias();
//		ejecutarClienteMunicipios();
//		ejecutarClienteEstaciones();
//		ejecutarClienteEspaciosNaturales();
//		ejecutarClienteEspNatMunicipios();
//		
//		mostrarMunicipios.setArrayProvincias(null);
//		mostrarMunicipios.setArrayMunicipios(null);
//		mostrarMunicipios.setArrayProvincias(arrayProvincias);
//		mostrarMunicipios.setArrayMunicipios(arrayMunicipios);
//		
//		mostrarEspaciosNaturales.setArrayProvincias(null);
//		mostrarEspaciosNaturales.setArrayEspNatMunicipios(null);
//		mostrarEspaciosNaturales.setArrayEspaciosNaturales(null);
//		mostrarEspaciosNaturales.setArrayEspNatMunicipios(null);
//		mostrarEspaciosNaturales.setArrayProvincias(arrayProvincias);
//		mostrarEspaciosNaturales.setArrayEspNatMunicipios(arrayEspNatMunicipios);
//		mostrarEspaciosNaturales.setArrayEspaciosNaturales(arrayEspaciosNaturales);
//		mostrarEspaciosNaturales.setArrayEspNatMunicipios(arrayEspNatMunicipios);
//		
//		mostrarEstaciones.setArrayProvincias(null);
//		mostrarEstaciones.setArrayMunicipios(null);
//		mostrarEstaciones.setArrayEstaciones(null);
//		mostrarEstaciones.setArrayProvincias(arrayProvincias);
//		mostrarEstaciones.setArrayMunicipios(arrayMunicipios);
//		mostrarEstaciones.setArrayEstaciones(arrayEstaciones);
//	}

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