package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;
import Modelo.Hibernate.ObjectExtras.ToString;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class frameEspaciosNaturales extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	EspaciosNaturales espNat = null;
	ArrayList<Estaciones> arrayEstaciones = new ArrayList<Estaciones>();
	ArrayList<MunEspNa> arrayEspNatMunicipios = new ArrayList<MunEspNa>();
	
	
	private JScrollPane scrollPaneDatos;
	private JComboBox comboBox;

	private String datos = "";
	private String ica = "";
	private JLabel lblICAEstacion;
	private JTextField txtICA;
	private JTextArea txtDatos;
	private JLabel lblPlaya;
	
	private boolean existeEstacion = false;
	

	/**
	 * Create the frame.
	 */
	public frameEspaciosNaturales(EspaciosNaturales espNat, ArrayList<MunEspNa> arrayEspNatMunicipios, ArrayList<Estaciones> arrayEstaciones)
			throws HeadlessException {
		this.espNat = espNat;
		this.arrayEspNatMunicipios = arrayEspNatMunicipios;
		this.arrayEstaciones = arrayEstaciones;
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(850, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstacion = new JLabel("Estación:");
		lblEstacion.setBounds(10, 75, 70, 14);
		contentPane.add(lblEstacion);
		
		comboBox = new JComboBox();
		comboBox.setBounds(90, 71, 161, 22);
		comboBox.addActionListener(this);
		contentPane.add(comboBox);
		
		txtDatos = new JTextArea();
		txtDatos.setEditable(false);
		txtDatos.setLineWrap(true);
		txtDatos.setWrapStyleWord(true);
		txtDatos.setBounds(10, 36, 414, 364);
		contentPane.add(txtDatos);

		scrollPaneDatos = new JScrollPane(txtDatos);
		scrollPaneDatos.setBounds(10, 100, 400, 250);
		getContentPane().add(scrollPaneDatos, BorderLayout.CENTER);
		getContentPane().add(scrollPaneDatos);
		scrollPaneDatos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		lblICAEstacion = new JLabel("ICA Estación:");
		lblICAEstacion.setBounds(10, 364, 89, 14);
		contentPane.add(lblICAEstacion);
		
		txtICA = new JTextField();
		txtICA.setEditable(false);
		txtICA.setBounds(109, 361, 161, 20);
		contentPane.add(txtICA);
		txtICA.setColumns(10);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCerrar.setBounds(321, 360, 89, 23);
		contentPane.add(btnCerrar);
		
		
		lblPlaya = new JLabel(espNat.getNombre());
		lblPlaya.setForeground(Color.BLUE);
		lblPlaya.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		lblPlaya.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlaya.setBounds(10, 11, 400, 49);
		contentPane.add(lblPlaya);
		
//		comboBox.setSelectedItem(0);
		
		for (MunEspNa espNatMun : arrayEspNatMunicipios) {							
			if(espNatMun.getEspaciosNaturales().getCodEspNatural() == espNat.getCodEspNatural()) {							
				int i = 0;
				for (Estaciones est : arrayEstaciones) {							
					if(espNatMun.getMunicipios().getCodMun() == est.getMunicipios().getCodMun()) {
						//----------------------------------------------
						existeEstacion = true;
						comboBox.addItem(est.getNombre());
						//----------------------------------------------										
					}
				}
			}
		}
		
//		for (Estaciones est : arrayEstaciones) {
//			if(est.getMunicipios().getCodMun() == espNat.getCodEspNatural()) {
//				existeEstacion = true;
//				comboBox.addItem(est.getNombre());
//			}
//		}
		datos = ToString.toStringFormat(espNat, true, true) + "\n";
		mostrarTxt(datos);
		
		if(!existeEstacion) {
			ica = "No tiene estación";
			mostrarICA(ica);
		}else {
			comboBox.setSelectedIndex(0);
			for (Estaciones est : arrayEstaciones) {	
				if(comboBox.getSelectedItem().equals(est.getNombre())) {
//					datos = ToString.toStringFormat(municipio, true, true) + "\n";
					ica = est.getIcaEstacion();
				}
			}
			mostrarICA(ica);
		}
				
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == comboBox) {
			
			datos = "";
			ica = "";
			
			for (Estaciones est : arrayEstaciones) {	
				if(comboBox.getSelectedItem().equals(est.getNombre())) {
//					datos = ToString.toStringFormat(municipio, true, true) + "\n";
					ica = est.getIcaEstacion();
				}
			}
			
			if(comboBox.getSelectedItem().equals("")) {
				ica = "Selecciona una estación";
			}
			
			mostrarICA(ica);
		}
		
	}
	
	public void mostrarTxt (String texto) {
		this.txtDatos.setText(texto);
	}
	
	public void mostrarICA (String texto) {
		this.txtICA.setText(texto);
	}
}
