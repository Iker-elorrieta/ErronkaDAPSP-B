package Test.PSP;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Controlador.Servidor;
import Vista.MostrarDatosCliente;

public class TestCliente {
	
	Servidor serv = new Servidor();
	MostrarDatosCliente cli1 = null;
	MostrarDatosCliente cli2 = null;

		@Test
		public void testCliente() {
			serv.start();
			try {
				cli1 = new MostrarDatosCliente();
				cli1.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			assertEquals(true, cli1.isProvTest());
			assertEquals(true, cli1.isMunTest());
			
//			serv.start();
//			try {
//				cli2 = new MostrarDatosCliente();
//				cli2.setVisible(true);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			resultado = cli2.ejecutarClienteProvincias("prueba");
//			esperado = 1;			
//			assertNotEquals(true, resultado);
		}
	
}
