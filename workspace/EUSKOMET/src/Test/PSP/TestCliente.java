package Test.PSP;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Controlador.Servidor;
import Vista.MostrarDatosCliente;

public class TestCliente {
	
	Servidor serv = new Servidor();
	MostrarDatosCliente cli = null;

		@Test
		public void testCliente() {
			serv.start();
			
			try {
				cli = new MostrarDatosCliente();
				cli.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int resultado = cli.ejecutarClienteProvincias("euskomet");
			int esperado = 1;			
			assertEquals(esperado, resultado);
			
			resultado = cli.ejecutarClienteProvincias("prueba");
			esperado = 1;			
			assertNotEquals(esperado, resultado);
		}
	
}
