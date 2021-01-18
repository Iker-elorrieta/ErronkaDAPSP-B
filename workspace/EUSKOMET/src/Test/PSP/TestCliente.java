package Test.PSP;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Vista.MostrarDatosCliente;

public class TestCliente {

		@Test
		public void testCliente() {
			
			MostrarDatosCliente frame = null;
			try {
				frame = new MostrarDatosCliente();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			int resultado = frame.ejecutarCliente();
			int esperado = 1;
			
			assertEquals(esperado, resultado);
		}
	
}
