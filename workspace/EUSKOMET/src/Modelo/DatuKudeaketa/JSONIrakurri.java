package Modelo.DatuKudeaketa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

public class JSONIrakurri {

	private File JSONDeskargatuFixed(String url) {

		String name = "lineadecodigo.json";
		String folder = "descargas/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return null;
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
			int ind = 1;
			while (b != -1) {
				b = in.read();
				if (ind > 13) {
					if (b != -1 && !Character.toString(b).equals("]")) {
						out.write(b);
					}
					else {
						out.write(b);
						break;
					}
				}
				ind++;
			}
			out.close();
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return file;
	}

	private File JSONDeskargatu(String url) {

		String name = "lineadecodigo.json";
		String folder = "descargas/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return null;
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
				if (b != -1) {
					out.write(b);
				}
			}
			out.close();
			in.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return file;
	}

	public void EstazioakIrakurri() {

		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json");

		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					while (jr.hasNext()) {
						System.out.println(jr.nextName() + ": " + jr.nextString());
					}
					jr.endObject();
				}
				jr.endArray();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if (!e.getMessage().contains("Expected BEGIN_ARRAY but was END_DOCUMENT")) {
				e.printStackTrace();
			}
			else {
				return;
			}
		}

	}


	// METODO EN DESUSO
	public static void dumpJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			System.out.println("Es objeto");
			JsonObject obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String,JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String,JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String,JsonElement> entrada = iter.next();
				System.out.println("Clave: " + entrada.getKey());
				System.out.println("Valor:");
				dumpJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			JsonArray array = elemento.getAsJsonArray();
			System.out.println("Es array. Numero de elementos: " + array.size());
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			System.out.println("Es primitiva");
			JsonPrimitive valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
				System.out.println("Es booleano: " + valor.getAsBoolean());
			} else if (valor.isNumber()) {
				System.out.println("Es numero: " + valor.getAsNumber());
			} else if (valor.isString()) {
				System.out.println("Es texto: " + valor.getAsString());
			}
		} else if (elemento.isJsonNull()) {
			System.out.println("Es NULL");
		} else {
			System.out.println("Es otra cosa");
		}
	}

}
