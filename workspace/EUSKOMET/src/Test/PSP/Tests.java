package Test.PSP;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controlador.Server;
import Vista.ClienteAPP;

class Tests {

	Server serv = new Server();
	ClienteAPP cli = null;
	
	@Test
	public void testClienteServidor() {
		serv.start();

		cli = new ClienteAPP();
		
		assertTrue(cli.isProvinciasTest());
		assertTrue(cli.isMunicipiosTest());
		assertTrue(cli.isEspaciosNaturalesTest());
		assertTrue(cli.isEstacionesTest());
		assertTrue(cli.isEspNatMunicipiosTest());
		
		assertTrue(cli.isClienteTest());
	}

}
