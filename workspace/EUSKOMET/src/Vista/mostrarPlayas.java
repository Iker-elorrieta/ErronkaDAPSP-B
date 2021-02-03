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
import javax.swing.JCheckBox;
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
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;
import Modelo.Hibernate.ObjectExtras.ToString;

public class mostrarPlayas extends JPanel implements ActionListener {

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
	private JCheckBox chkbDescripcion;

	Scanner sc = new Scanner(System.in); // abrir escaner

	ArrayList<Provincias> arrayProvincias = ClienteAPP.getArrayProvincias();
	ArrayList<Municipios> arrayMunicipios = ClienteAPP.getArrayMunicipios();
	ArrayList<EspaciosNaturales> arrayEspaciosNaturales = ClienteAPP.getArrayEspaciosNaturales();
	ArrayList<MunEspNa> arrayEspNatMunicipios = ClienteAPP.getArrayEspNatMunicipios();
	ArrayList<Estaciones> arrayEstaciones = ClienteAPP.getArrayEstaciones();
	private ArrayList <String> itemsSeleccionados = new ArrayList<String>();

	private int cod_prov = 0;
	private int cod_mun = 0;
	private String datos = "";

	/**
	 * Create the panel.
	 */
	public mostrarPlayas() {
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

		JLabel lblMunicipio = new JLabel("Escoja uno o más espacios naturales:");
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
		
//		chkbDescripcion = new JCheckBox("Ver descripción");
//		chkbDescripcion.setBounds(585, 36, 160, 22);
//		chkbDescripcion.addActionListener(this);
//		add(chkbDescripcion);

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
				JOptionPane.showMessageDialog(this, "Por favor, seleccione un espacio natural", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
			}else {
				datos = "";
				itemsSeleccionados.clear();
				
				try {
					itemsSeleccionados = (ArrayList<String>) list.getSelectedValuesList();
	
					for (String elem : itemsSeleccionados) {
						for (EspaciosNaturales espNat : arrayEspaciosNaturales) {	
							if(elem.toString().equals(espNat.getNombre())) {
//								datos += ToString.toStringFormat(espNat, false) + "\n";
//								if(chkbDescripcion.isSelected()) {
//									datos += ToString.toStringFormat(espNat, true) + "\n";
//								}else {
									datos += ToString.toStringFormat(espNat, false) + "\n";
//								}
//								framePlayas framePlayas = new framePlayas(espNat, arrayEspNatMunicipios, arrayEstaciones);
//								framePlayas.setVisible(true);
								
								frameEspaciosNaturales frameEspaciosNaturales = new frameEspaciosNaturales(espNat, arrayEspNatMunicipios, arrayEstaciones);
								frameEspaciosNaturales.setVisible(true);
							}
						}
	
					}
					mostrarTxt(datos);
				}catch (Exception e1) {
//					JOptionPane.showMessageDialog(this, "Por favor, seleccione una estación", "EUSKOMET: Error", JOptionPane.ERROR_MESSAGE);
					
					for (EspaciosNaturales espNat : arrayEspaciosNaturales) {		
//								datos += ToString.toStringFormat(espNat, false) + "\n";
//						if(chkbDescripcion.isSelected()) {
//							datos += ToString.toStringFormat(espNat, true) + "\n";
//						}else {
							datos += ToString.toStringFormat(espNat, false) + "\n";
//						}
							}
						mostrarTxt(datos);
						
				}
			}
		}

		if (e.getSource() == cbProvincia) {

//			if(((String) cbProvincia.getSelectedItem()).equalsIgnoreCase("")) { 	
//
//				System.out.println("[EspNat]\t--\tcbProvincia\t--\tNo se ha seleccionado ningún elemento");
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

						for (MunEspNa espNatMun : arrayEspNatMunicipios) {							
							if(espNatMun.getMunicipios().getCodMun() == this.cod_mun) {							
								int i = 0;
								for (EspaciosNaturales espNat : arrayEspaciosNaturales) {							
									if(espNat.getCodEspNatural() == espNatMun.getEspaciosNaturales().getCodEspNatural()) {
										//----------------------------------------------
										if(espNat.getTipo().equals("Playas")) {
											listModel.add(i, espNat.getNombre());
											i++;
										}
										//----------------------------------------------										
									}
								}
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

						for (MunEspNa espNatMun : arrayEspNatMunicipios) {							
							if(espNatMun.getMunicipios().getCodMun() == this.cod_mun) {							
								int i = 0;
								for (EspaciosNaturales espNat : arrayEspaciosNaturales) {							
									if(espNat.getCodEspNatural() == espNatMun.getEspaciosNaturales().getCodEspNatural()) {	
										//----------------------------------------------
										if(espNat.getTipo().equals("Playas")) {
											listModel.add(i, espNat.getNombre());
											i++;
										}
										//----------------------------------------------
									}
								}
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

						for (MunEspNa espNatMun : arrayEspNatMunicipios) {							
							if(espNatMun.getMunicipios().getCodMun() == this.cod_mun) {							
								int i = 0;
								for (EspaciosNaturales espNat : arrayEspaciosNaturales) {							
									if(espNat.getCodEspNatural() == espNatMun.getEspaciosNaturales().getCodEspNatural()) {	
										//----------------------------------------------
										if(espNat.getTipo().equals("Playas")) {
											listModel.add(i, espNat.getNombre());
											i++;
										}
										//----------------------------------------------
									}
								}
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

	public void setArrayEspaciosNaturales(ArrayList<EspaciosNaturales> arrayEspaciosNaturales) {
		if(arrayEspaciosNaturales != null) {
			this.arrayEspaciosNaturales = arrayEspaciosNaturales;
		}else {
			this.arrayEspaciosNaturales.clear();
		}
	}

	public void setArrayEspNatMunicipios(ArrayList<MunEspNa> arrayEspNatMunicipios) {
		if(arrayEspaciosNaturales != null) {
			this.arrayEspaciosNaturales = arrayEspaciosNaturales;
		}else {
			this.arrayEspaciosNaturales.clear();
		}
	}

	


}
