package Interfazea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import DatuKudeaketa.JSONIrakurri;

public class Launcher {

	public static void main(String[] args) {
		
		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2020/es_def/adjuntos/estaciones.json"; 
		String name = "lineadecodigo.json";
		String folder = "descargas/";
		File dir = new File(folder);
		if (!dir.exists())
		  if (!dir.mkdir())
		    return; // no se pudo crear la carpeta de destino
		File file = new File(folder + name);
		URLConnection conn = null;
		try {
			conn = new URL(url).openConnection();
			conn.connect();
			System.out.println("\nempezando descarga: \n");
			System.out.println(">> URL: " + url);
			System.out.println(">> Nombre: " + name);
			System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");
			InputStream in = conn.getInputStream();
			OutputStream out = new FileOutputStream(file);
			int b = 0;
			while (b != -1) {
			  b = in.read();
			  if (b != -1)
			    out.write(b);
			}
			out.close();
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JSONParser parser = new JSONParser();
		

		try {

			Object obj = parser.parse(new FileReader(file));

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
