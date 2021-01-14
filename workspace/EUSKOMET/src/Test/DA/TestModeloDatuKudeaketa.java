package Test.DA;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import Modelo.DatuKudeaketa.JSONIrakurri;

public class TestModeloDatuKudeaketa {
	
	@Test
	public void testJSONDeskargatu() {
		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/datos_indice/3_DE_MARZO.json";
		String urlmal = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/datos_indice/3_DE_MA.json";
		JSONIrakurri jsi = new JSONIrakurri();
		assertNotNull(jsi.JSONDeskargatu(url));
		assertNull(jsi.JSONDeskargatu(urlmal));
	}

}
