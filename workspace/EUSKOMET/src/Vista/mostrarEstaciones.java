package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import Controlador.Cliente;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;
import Modelo.Hibernate.ObjectExtras.ToString;

public class mostrarEstaciones extends JPanel implements ActionListener {

	private final static int PUERTO = 5000;
	//	private final String HOST = "127.0.0.1";
	private final static String HOST = "localhost";

	Cliente cliente  = null;
	Socket socket = null;
	private String bbdd = "euskomet";

	private static JTextArea txtDatos;
	private JScrollPane scrollPaneDatos;
	private JScrollPane scrollPaneList;
	private JComboBox cbProvincia;
	private JList list;
	private JButton btnBuscar;
	private JButton btnVolver;
	private DefaultListModel listModel;

	Scanner sc = new Scanner(System.in); // abrir escaner

	ArrayList<Provincias> arrayProvincias = ClienteAPP.getArrayProvincias();
	ArrayList<Municipios> arrayMunicipios = ClienteAPP.getArrayMunicipios();
	ArrayList<Estaciones> arrayEstaciones = ClienteAPP.getArrayEstaciones();
	private ArrayList <String> itemsSeleccionados = new ArrayList<String>();

	private int cod_prov = 0;
	private int cod_mun = 0;
	private String datos = "";

	/**
	 * Create the panel.
	 */
	public mostrarEstaciones() {
		setLayout(null);
		iniciarComponentes();
	}

	private void iniciarComponentes() {

		txtDatos = new JTextArea();
		txtDatos.setBounds(1, 1, 655, 447);
		add(txtDatos);

		scrollPaneDatos = new JScrollPane(txtDatos);
		scrollPaneDatos.setBounds(10, 11, 400, 339);
		add(scrollPaneDatos, BorderLayout.CENTER);
		add(scrollPaneDatos);
		scrollPaneDatos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		cbProvincia = new JComboBox();
		cbProvincia.setBounds(420, 36, 160, 22);
		cbProvincia.addActionListener(this);
		add(cbProvincia);		

		JLabel lblProvincia = new JLabel("Escoja una provincia:");
		lblProvincia.setBounds(420, 11, 198, 14);
		add(lblProvincia);

		JLabel lblMunicipio = new JLabel("Escoja una o más estaciones:");
		lblMunicipio.setBounds(420, 69, 250, 14);
		add(lblMunicipio);

		list = new JList<String>();
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.setBounds(10, 11, 176, 239);
		add(list);

		scrollPaneList = new JScrollPane(list);
		scrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneList.setBounds(420, 94, 273, 189); // Los bounds TIENEN que ser los mismos del JList
		add(scrollPaneList);

		//Crear un objeto DefaultListModel
		listModel = new DefaultListModel();
		list.setModel(listModel);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(604, 294, 89, 23);
		btnBuscar.addActionListener(this);
		add(btnBuscar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(604, 327, 89, 23);
		btnVolver.addActionListener(this);
		add(btnVolver);

		JLabel lblCntrl = new JLabel("Selección múltiple: CNTRL + CLICK");
		lblCntrl.setHorizontalAlignment(SwingConstants.CENTER);
		lblCntrl.setForeground(Color.BLUE);
		lblCntrl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblCntrl.setBounds(420, 294, 176, 22);
		add(lblCntrl);
		
		JLabel lblNoSelection = new JLabel("Anular selección: CNTRL + CLICK");
		lblNoSelection.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoSelection.setForeground(Color.BLUE);
		lblNoSelection.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNoSelection.setBounds(420, 327, 176, 22);
		add(lblNoSelection);

		cbProvincia.addItem("");
		cbProvincia.setSelectedItem(0);
		String resultado = "";
		for (Provincias prov : arrayProvincias) {	
			cbProvincia.addItem(prov.getNombre());
		}
	}


	public static void mostrarTxt (String texto) {
		txtDatos.setText(texto);
	}

	public JTextArea getTxtDatos() {
		return txtDatos;
	}

	public void setTxtDatos(JTextArea txtDatos) {
		this.txtDatos = txtDatos;
	}

	public void actionPerformed(ActionEvent e) { 

		if (e.getSource() == btnVolver) {
			setVisible(false);
			ClienteAPP.menuPrincipal.setVisible(true);
		}

		if (e.getSource() == btnBuscar) {

			if(itemsSeleccionados == null || cbProvincia.getSelectedIndex() == 0) {
				mostrarTxt("");
				JOptionPane.showMessageDialog(this, "Por favor, seleccione una estación", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
			}else {
				datos = "";
				itemsSeleccionados.clear();
				
				
				try {
					itemsSeleccionados = (ArrayList<String>) list.getSelectedValuesList();
					
					System.out.println(itemsSeleccionados);
					
					for (String elem : itemsSeleccionados) {
						for (Estaciones est : arrayEstaciones) {	
							if(elem.toString().equals(est.getNombre())) {
								datos += ToString.toStringFormat(est, false) + "\n";
							}
						}
	
					}
					mostrarTxt(datos);
				}catch (Exception e1) {
//					JOptionPane.showMessageDialog(this, "Por favor, seleccione una estación", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
					
						for (Estaciones est : arrayEstaciones) {	
								datos += ToString.toStringFormat(est, false) + "\n";
							}
						mostrarTxt(datos);
						
				}
			}
		}

		if (e.getSource() == cbProvincia) {

//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("")) { 	
//				listModel.removeAllElements();
//				System.out.println("[Estaciones]\t--\tcbProvincia\t--\tNo se ha seleccionado ningún elemento");
//
//			}

			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Bizkaia")) {

				listModel.removeAllElements();

				for (Provincias prov : arrayProvincias) {											
					if(prov.getNombre().equalsIgnoreCase("Bizkaia")) {
						cod_prov = prov.getCodProv();									
					}
				}

				for (Municipios mun : arrayMunicipios) {							
					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
						cod_mun = mun.getCodMun();
						int i = 0;
						for (Estaciones est : arrayEstaciones) {								
							if(est.getMunicipios().getCodMun() == this.cod_mun) {	
								listModel.add(i, est.getNombre());
								i++;
							}						

						}

					}
				}
			}


			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Gipuzkoa")) {

				listModel.removeAllElements();

				for (Provincias prov : arrayProvincias) {											
					if(prov.getNombre().equalsIgnoreCase("Gipuzkoa")) {
						cod_prov = prov.getCodProv();									
					}
				}

				for (Municipios mun : arrayMunicipios) {							
					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
						cod_mun = mun.getCodMun();
						int i = 0;
						for (Estaciones est : arrayEstaciones) {								
							if(est.getMunicipios().getCodMun() == this.cod_mun) {	
								listModel.add(i, est.getNombre());
								i++;
							}						

						}

					}
				}
			}


			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Araba")) {

				listModel.removeAllElements();

				for (Provincias prov : arrayProvincias) {											
					if(prov.getNombre().equalsIgnoreCase("Araba")) {
						cod_prov = prov.getCodProv();									
					}
				}

				for (Municipios mun : arrayMunicipios) {							
					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
						cod_mun = mun.getCodMun();
						int i = 0;
						for (Estaciones est : arrayEstaciones) {								
							if(est.getMunicipios().getCodMun() == this.cod_mun) {	
								listModel.add(i, est.getNombre());
								i++;
							}						

						}

					}
				}
			}

		}

	}

	public void setArrayProvincias(ArrayList<Provincias> arrayProvincias) {
		if(arrayProvincias != null) {
			this.arrayProvincias = arrayProvincias;
		}else {
			this.arrayProvincias.clear();
		}
	}

	public void setArrayMunicipios(ArrayList<Municipios> arrayMunicipios) {
		if(arrayMunicipios != null) {
			this.arrayMunicipios = arrayMunicipios;
		}else {
			this.arrayMunicipios.clear();
		}
	}

	public void setArrayEstaciones(ArrayList<Estaciones> arrayEstaciones) {
		if(arrayEstaciones != null) {
			this.arrayEstaciones = arrayEstaciones;
		}else {
			this.arrayEstaciones.clear();
		}
	}



}













//package Vista;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.Scanner;
//
//import javax.swing.DefaultListModel;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JList;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JScrollPane;
//import javax.swing.JTextArea;
//import javax.swing.ListSelectionModel;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.SwingConstants;
//
//import Controlador.Cliente;
//import Modelo.Hibernate.Select;
//import Modelo.Hibernate.Object.Estaciones;
//import Modelo.Hibernate.Object.Municipios;
//import Modelo.Hibernate.Object.Provincias;
//import Modelo.Hibernate.ObjectExtras.ToString;
//
//public class mostrarEstaciones extends JPanel implements ActionListener {
//
//	private final static int PUERTO = 5000;
//	//	private final String HOST = "127.0.0.1";
//	private final static String HOST = "localhost";
//
//	Cliente cliente  = null;
//	Socket socket = null;
//	private String bbdd = "euskomet";
//
//	private static JTextArea txtDatos;
//	private JScrollPane scrollPaneDatos;
//	private JScrollPane scrollPaneList;
//	private JComboBox cbProvincia;
//	private JList list;
//	private JButton btnBuscar;
//	private JButton btnVolver;
//	private DefaultListModel listModel;
//	private JCheckBox chkbDescripcion;
//	private JComboBox cbEstacion;
//
//	Scanner sc = new Scanner(System.in); // abrir escaner
//
//	ArrayList<Provincias> arrayProvincias = ClienteAPP.getArrayProvincias();
//	ArrayList<Municipios> arrayMunicipios = ClienteAPP.getArrayMunicipios();
//	ArrayList<Estaciones> arrayEstaciones = ClienteAPP.getArrayEstaciones();
//	private ArrayList <String> itemsSeleccionados = new ArrayList<String>();
//
//	LinkedHashMap<Integer, Municipios> lhmMuni = Select.obtMunicipios();
//	
//	private int cod_prov = 0;
//	private int cod_mun = 0;
//	private String datos = "";
//
//	/**
//	 * Create the panel.
//	 */
//	public mostrarEstaciones() {
//		setLayout(null);
//		iniciarComponentes();
//	}
//
//	private void iniciarComponentes() {
//
//		txtDatos = new JTextArea();
//		txtDatos.setBounds(1, 1, 655, 447);
//		add(txtDatos);
//
//		scrollPaneDatos = new JScrollPane(txtDatos);
//		scrollPaneDatos.setBounds(10, 11, 400, 339);
//		add(scrollPaneDatos, BorderLayout.CENTER);
//		add(scrollPaneDatos);
//		scrollPaneDatos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//
//		cbProvincia = new JComboBox();
//		cbProvincia.setBounds(420, 36, 160, 22);
//		cbProvincia.addActionListener(this);
//		add(cbProvincia);		
//
//		JLabel lblProvincia = new JLabel("Escoja una provincia:");
//		lblProvincia.setBounds(420, 11, 198, 14);
//		add(lblProvincia);
//
//		JLabel lblMunicipio = new JLabel("Escoja uno o más municipios:");
//		lblMunicipio.setBounds(420, 69, 250, 14);
//		add(lblMunicipio);
//
//		list = new JList<String>();
//		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		list.setBounds(10, 11, 176, 239);
//		add(list);
//
//		scrollPaneList = new JScrollPane(list);
//		scrollPaneList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPaneList.setBounds(420, 94, 273, 189); // Los bounds TIENEN que ser los mismos del JList
//		add(scrollPaneList);
//
//		//Crear un objeto DefaultListModel
//		listModel = new DefaultListModel();
//		list.setModel(listModel);
//
//		btnBuscar = new JButton("Buscar");
//		btnBuscar.setBounds(604, 294, 89, 23);
//		btnBuscar.addActionListener(this);
//		add(btnBuscar);
//
//		btnVolver = new JButton("Volver");
//		btnVolver.setBounds(604, 327, 89, 23);
//		btnVolver.addActionListener(this);
//		add(btnVolver);
//
//		JLabel lblCntrl = new JLabel("Selección múltiple: CNTRL + CLICK");
//		lblCntrl.setHorizontalAlignment(SwingConstants.CENTER);
//		lblCntrl.setForeground(Color.BLUE);
//		lblCntrl.setFont(new Font("Tahoma", Font.PLAIN, 11));
//		lblCntrl.setBounds(420, 294, 176, 22);
//		add(lblCntrl);
//		
////		JLabel lblNoSelection = new JLabel("Anular selección: CNTRL + CLICK");
////		lblNoSelection.setHorizontalAlignment(SwingConstants.CENTER);
////		lblNoSelection.setForeground(Color.BLUE);
////		lblNoSelection.setFont(new Font("Tahoma", Font.PLAIN, 11));
////		lblNoSelection.setBounds(420, 327, 176, 22);
////		add(lblNoSelection);
//		
//		chkbDescripcion = new JCheckBox("Ver descripción");
//		chkbDescripcion.setBounds(585, 36, 160, 22);
//		chkbDescripcion.addActionListener(this);
//		add(chkbDescripcion);
//		
//		cbEstacion = new JComboBox();
//		cbEstacion.setBounds(420, 327, 176, 22);
//		cbEstacion.addActionListener(this);
//		add(cbEstacion);	
//		
//		
//		cbProvincia.addItem("");
//		cbProvincia.setSelectedItem(0);
//		String resultado = "";
//		for (Provincias prov : arrayProvincias) {	
//			cbProvincia.addItem(prov.getNombre());
//		}
//	}
//
//	public static void mostrarTxt (String texto) {
//		txtDatos.setText(texto);
//	}
//
//	public JTextArea getTxtDatos() {
//		return txtDatos;
//	}
//
//	public void setTxtDatos(JTextArea txtDatos) {
//		this.txtDatos = txtDatos;
//	}
//
//	public void actionPerformed(ActionEvent e) { 
//
//		if (e.getSource() == btnVolver) {
//			setVisible(false);
//			ClienteAPP.menuPrincipal.setVisible(true);
//		}
//
//		if (e.getSource() == btnBuscar) {
//			
//			if(itemsSeleccionados == null || cbProvincia.getSelectedIndex() == 0) {
//				mostrarTxt("");
//				JOptionPane.showMessageDialog(this, "Por favor, seleccione una provincia", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
//			}else {
//				datos = "";
//				itemsSeleccionados.clear();
//				
//				try {
//					itemsSeleccionados = (ArrayList<String>) list.getSelectedValuesList();
//					System.out.println(itemsSeleccionados);
//					
//					for (String elem : itemsSeleccionados) {
//						System.out.println(elem);
//						System.out.println("arrayEstaciones.size() = " + arrayEstaciones.size());
//							int i = 0;
//								for (Estaciones est : arrayEstaciones) {
//									System.out.println("Entra en el seguundo for");
//									System.out.println("Nombre estacion: " + est.getNombre());
//									System.out.println("Nombre municipio estacion: " + est.getMunicipios().getNombre());
//									if(elem.equals(lhmMuni)) {	
//										cbEstacion.addItem(est.getNombre());
//										System.out.println(est.getNombre());
//										i++;
//									}	
//
//							}
//							
////							if(elem.toString().equals(mun.getNombre())) {
//////							datos += mun.toString() + "\n";
////								cbProvincia.addItem(prov.getNombre());
//////								if(chkbDescripcion.isSelected()) {
//////									datos += ToString.toStringFormat(mun, true) + "\n";
//////								}else {
//////									datos += ToString.toStringFormat(mun, false) + "\n";
//////								}
////								
////							}
//	
//					}
////					mostrarTxt(datos);
//				}catch (Exception e1) {
////					JOptionPane.showMessageDialog(this, "Por favor, seleccione una estación", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
//					
//					for (Municipios mun : arrayMunicipios) {		
////								datos += ToString.toStringFormat(mun, true) + "\n";
//						if(chkbDescripcion.isSelected()) {
//							datos += ToString.toStringFormat(mun, true) + "\n";
//						}else {
//							datos += ToString.toStringFormat(mun, false) + "\n";
//						}
//							}
////						mostrarTxt(datos);
//						
//				}
//			}
//		}
//
//		if (e.getSource() == cbProvincia) {
//
////			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("")) { 	
////				listModel.removeAllElements();
////				System.out.println("[Municipios]\t--\tcbProvincia\t--\tNo se ha seleccionado ningún elemento");
////
////			}
//
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Bizkaia")) {
//
//				listModel.removeAllElements();
//
//				for (Provincias prov : arrayProvincias) {											
//					if(prov.getNombre().equalsIgnoreCase("Bizkaia")) {
//						cod_prov = prov.getCodProv();									
//					}
//				}
//
//				int i = 0;
//				for (Municipios mun : arrayMunicipios) {							
//					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
//						listModel.add(i, mun.getNombre());
//						i++;
//					}
//				}
//			}
//
//
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Gipuzkoa")) {
//
//				listModel.removeAllElements();
//
//				for (Provincias prov : arrayProvincias) {											
//					if(prov.getNombre().equalsIgnoreCase("Gipuzkoa")) {
//						cod_prov = prov.getCodProv();									
//					}
//				}
//
//				int i = 0;
//				for (Municipios mun : arrayMunicipios) {							
//					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
//						listModel.add(i, mun.getNombre());
//						i++;
//					}
//				}
//			}
//
//
//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("Araba")) {
//
//				listModel.removeAllElements();
//
//				for (Provincias prov : arrayProvincias) {											
//					if(prov.getNombre().equalsIgnoreCase("Araba")) {
//						cod_prov = prov.getCodProv();									
//					}
//				}
//
//				int i = 0;
//				for (Municipios mun : arrayMunicipios) {							
//					if(mun.getProvincias().getCodProv() == this.cod_prov) {										
//						listModel.add(i, mun.getNombre());
//						i++;
//					}
//				}
//			}
//
//		}
//
//	}
//
//	public void setArrayProvincias(ArrayList<Provincias> arrayProvincias) {
//		
//		if(arrayProvincias != null) {
//			this.arrayProvincias = arrayProvincias;
//		}else {
//			this.arrayProvincias.clear();
//		}
//		
//	}
//
//	public void setArrayMunicipios(ArrayList<Municipios> arrayMunicipios) {
//		if(arrayMunicipios != null) {
//			this.arrayMunicipios = arrayMunicipios;
//		}else {
//			this.arrayMunicipios.clear();
//		}
//	}
//	
//	
//
//
//}
//
