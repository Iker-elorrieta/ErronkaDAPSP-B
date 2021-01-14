package Test.DA;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Vista.Launcher;

public class TestVista {

	@Test
	public void testLauncher() {
		assertTrue(Launcher.main(null) == 0);
	}
	
}
