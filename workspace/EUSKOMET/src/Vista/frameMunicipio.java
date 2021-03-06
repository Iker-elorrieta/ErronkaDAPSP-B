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

import Modelo.Hibernate.Object.Estaciones;
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

public class frameMunicipio extends JFrame implements ActionListener{

	private JPanel contentPane;
	
	Municipios municipio = null;
	ArrayList<Estaciones> arrayEstaciones = new ArrayList<Estaciones>();
	private JScrollPane scrollPaneDatos;
	private JComboBox comboBox;

	private String datos = "";
	private String ica = "";
	private JLabel lblICAEstacion;
	private JTextField txtICA;
	private JTextArea txtDatos;
	private JLabel lblMunicipio;
	
	private boolean existeEstacion = false;
	

	/**
	 * Create the frame.
	 */
	public frameMunicipio(Municipios municipio, ArrayList<Estaciones> arrayEstaciones)
			throws HeadlessException {
		this.municipio = municipio;
		this.arrayEstaciones = arrayEstaciones;
		
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(850, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstacion = new JLabel("Estaci�n:");
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
		
		lblICAEstacion = new JLabel("ICA Estaci�n:");
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
		
		
		lblMunicipio = new JLabel(municipio.getNombre());
		lblMunicipio.setForeground(Color.BLUE);
		lblMunicipio.setFont(new Font("Century Gothic", Font.PLAIN, 35));
		lblMunicipio.setHorizontalAlignment(SwingConstants.CENTER);
		lblMunicipio.setBounds(10, 11, 400, 49);
		contentPane.add(lblMunicipio);
		
		comboBox.addItem("");
		comboBox.setSelectedItem(0);
		for (Estaciones est : arrayEstaciones) {
			if(municipio.getCodMun() == est.getMunicipios().getCodMun()) {
				existeEstacion = true;
				comboBox.addItem(est.getNombre());
			}
		}
		datos = ToString.toStringFormat(municipio, true, true) + "\n";
		
		if(!existeEstacion) {
			ica = "No tiene estaci�n";
			mostrarICA(ica);
		}
		
		mostrarTxt(datos);
		
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
