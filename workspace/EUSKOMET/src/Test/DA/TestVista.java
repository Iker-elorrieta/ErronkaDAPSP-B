package Test.DA;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import Vista.Launcher;

public class TestVista {

	@Test
	public void testLauncher() {
		int resultado = Launcher.ejecutar();
		int esperado = 0;
		
		assertEquals(esperado, resultado);
	}
	
}
