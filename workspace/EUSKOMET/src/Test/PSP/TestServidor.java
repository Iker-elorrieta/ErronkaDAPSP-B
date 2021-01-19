package Test.PSP;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Controlador.Servidor;
import Vista.MostrarDatosCliente;

public class TestServidor {
	
	Servidor serv = new Servidor();
	MostrarDatosCliente frame = null;

		@Test
		public void testServidor() {
			serv.start();
			
			frame = new MostrarDatosCliente();
			frame.setVisible(true);
			
			assertTrue(serv.isTestServidorBueno());
			
			assertNotNull(frame.getTxtDatos());
		}
	
}