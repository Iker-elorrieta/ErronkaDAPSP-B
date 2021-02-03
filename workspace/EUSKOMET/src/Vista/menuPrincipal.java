package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class menuPrincipal extends JPanel implements ActionListener  {

	private JButton btnSelectMunicipios;
	private JButton btnEspaciosNaturales;
	private JButton btnEstaciones;
	private JButton btnPlayas;
	private JButton btnFavMun;


	public menuPrincipal() {
		setLayout(null);
		iniciarComponentes();
	}

	public void iniciarComponentes() {
		
		btnSelectMunicipios = new JButton("Municipios");
		btnSelectMunicipios.setBounds(250, 84, 198, 23);
		btnSelectMunicipios.addActionListener(this);
		add(btnSelectMunicipios);
		
		btnEspaciosNaturales = new JButton("Espacios naturales");
		btnEspaciosNaturales.setBounds(250, 118, 198, 23);
		btnEspaciosNaturales.addActionListener(this);
		add(btnEspaciosNaturales);
		
		btnEstaciones = new JButton("Estaciones");
		btnEstaciones.setBounds(250, 152, 198, 23);
		btnEstaciones.addActionListener(this);
		add(btnEstaciones);
		
		btnPlayas = new JButton("Playas");
		btnPlayas.setBounds(250, 186, 198, 23);
		btnPlayas.addActionListener(this);
		add(btnPlayas);
		
		btnFavMun = new JButton("Top 5 Municipios (favoritos)");
		btnFavMun.setBounds(250, 220, 198, 23);
		btnFavMun.addActionListener(this);
		add(btnFavMun);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSelectMunicipios) {
			setVisible(false);
			ClienteAPP.mostrarMunicipios.setVisible(true);
		}
		
		if (e.getSource() == btnEspaciosNaturales) {
			setVisible(false);
			ClienteAPP.mostrarEspaciosNaturales.setVisible(true);
		}
		
		if (e.getSource() == btnEstaciones) {
			setVisible(false);
			ClienteAPP.mostrarEstaciones.setVisible(true);
		}
		
		if (e.getSource() == btnPlayas) {
			setVisible(false);
			ClienteAPP.mostrarPlayas.setVisible(true);
		}
		
		if (e.getSource() == btnFavMun) {
			setVisible(false);
			ClienteAPP.mostrarTopMunicipios.setVisible(true);
		}
	}
}
