package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.DatuKudeaketa.Cliente;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MostrarDatosCliente extends JFrame {

	private JPanel contentPane;
	private final static int PUERTO = 5000;
	private final static String Host = "localhost";
	
	Cliente cliente  = null;
	Socket socket = null;

	private JTextArea txtDatos;
	private JScrollPane scrollPane;

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
		setBounds(100, 100, 500, 400);
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

		cliente = new Cliente();
//		socket = new Socket(,cliente.getPuerto());				
//		cliente = new Cliente(socket,textArea, txtMensaje,btnEnviar);
		
		ejecutarCliente();
	}

	public void ejecutarCliente() {
		Socket Cliente;
		try {
			Cliente = new Socket(Host, PUERTO);

			BufferedReader fentrada =  new BufferedReader (new InputStreamReader(Cliente.getInputStream()));

			// FLUJO PARA ENTRADA ESTANDAR
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String cadena;		

			cadena = in.readLine();
			txtDatos.setText(cadena);

			fentrada.close();
			System.out.println("Fin del envío... ");
			in.close();
			Cliente.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//

	public JTextArea getTxtDatos() {
		return txtDatos;
	}

	public void setTxtDatos(JTextArea txtDatos) {
		this.txtDatos = txtDatos;
	}
}
