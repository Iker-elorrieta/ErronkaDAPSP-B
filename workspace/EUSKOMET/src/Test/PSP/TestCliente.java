package Test.PSP;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Controlador.Cliente;
import Controlador.Server;
import Vista.ClienteAPP;

public class TestCliente {
	
	Server serv = new Server();
	ClienteAPP cli = null;
		
		@Test
		public void testCliente() {
			serv.start();
			
			for(int i = 0; i < 3; i++)
			cli = new ClienteAPP();
			
			assertTrue(cli.isProvinciasTest());
			assertTrue(cli.isMunicipiosTest());
			assertTrue(cli.isEspaciosNaturalesTest());
			assertTrue(cli.isEstacionesTest());
			assertTrue(cli.isEspNatMunicipiosTest());
			
			assertTrue(cli.isClienteTest());
		}
	
}
