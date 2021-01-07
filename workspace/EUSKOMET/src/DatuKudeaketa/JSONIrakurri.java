package DatuKudeaketa;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONIrakurri {

	public static void JSON() {
		JSONParser parser = new JSONParser();

		try {

			Object obj = parser.parse(new FileReader("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json"));

			JSONObject jsonObject = (JSONObject) obj;

			String blog = (String) jsonObject.get("Name");
			System.out.println(blog);

			String temas = (String) jsonObject.get("Province");
			System.out.println(temas);

			String inicio = (String) jsonObject.get("Town");
			System.out.println(inicio);

			/* loop array
			JSONArray tags = (JSONArray) jsonObject.get("Tags");
			Iterator<String> iterator = tags.iterator();
			while (iterator.hasNext()) {
				System.out.println(iterator.next());
			}*/

		} catch (FileNotFoundException e) {
			//manejo de error
		} catch (IOException e) {
			//manejo de error
		} catch (ParseException e) {
			//manejo de error
		}

	}

}
