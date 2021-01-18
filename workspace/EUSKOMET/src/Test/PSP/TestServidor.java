package Test.PSP;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Controlador.Servidor;

public class TestServidor {

		@Test
		public void testServidor() {
			Servidor s1 = new Servidor();
			int resultado = s1.iniciar();
			int esperado = 1;
			
			assertEquals(esperado, resultado);
		}
	
}