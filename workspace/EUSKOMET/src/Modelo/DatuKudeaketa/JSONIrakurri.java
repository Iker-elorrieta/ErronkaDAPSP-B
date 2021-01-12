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
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.stream.JsonReader;

import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.Municipio;

public class JSONIrakurri {

	private File JSONDeskargatuFixed(String url) {

		String name = "temp.json";
		String folder = "Descargas/";
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

		String name = "temp.json";
		String folder = "Descargas/";
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

	public LinkedHashMap<String, Estazioa> estazioakIrakurri() {

		LinkedHashMap<String, Estazioa> lhmEstazioa = new LinkedHashMap<String, Estazioa>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json");
		Estazioa est;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new Estazioa();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "Name":
							est.setIzena(jr.nextString());
							break;
						case "Province":
							est.setProbintzia(jr.nextString());
							break;
						case "Town":
							est.setHerria(jr.nextString());
							break;
						case "Address":
							est.setHelbidea(jr.nextString());
							break;
						case "CoordenatesXETRS89":
							est.setKoordenatuakX(jr.nextString());
							break;
						case "CoordenatesYETRS89":
							est.setKoordenatuakY(jr.nextString());
							break;
						case "Latitude":
							est.setLatitudea(jr.nextString());
							break;
						case "Longitude":
							est.setLongitudea(jr.nextString());
							break;
						};
					}
					lhmEstazioa.put(est.getIzena(), est);
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
				return lhmEstazioa;
			}
		}
		return lhmEstazioa;
	}

	public LinkedHashMap<String, EspaciosNaturales> espazioNaturalakIrakurri() {

		LinkedHashMap<String, EspaciosNaturales> lhmEstazioa = new LinkedHashMap<String, EspaciosNaturales>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json");
		EspaciosNaturales est;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new EspaciosNaturales();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "documentName":
							est.setNombre(jr.nextString());
							break;
						case "natureType":
							est.setTipo(jr.nextString());
							break;
						case "lonwgs84":
							String s1 = jr.nextString();
							est.setLongitud((s1.equals("") ? 0 : Double.parseDouble(s1)));
							break;
						case "latwgs84":
							String s2 = jr.nextString();
							est.setLatitud((s2.equals("") ? 0 : Double.parseDouble(s2)));
							break;
						case "municipalitycode":
							est.setCod_mun(jr.nextString());
							break;
						default:
							jr.nextString();
						};
					}
					lhmEstazioa.put(est.getNombre(), est);
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
				return lhmEstazioa;
			}
		}
		return lhmEstazioa;
	}

	public LinkedHashMap<String, Municipio> herriaIrakurri() {
		LinkedHashMap<String, Municipio> lhmMun = new LinkedHashMap<String, Municipio>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json");
		Municipio est;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new Municipio();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "municipalitycode":
							est.setCod_mun(jr.nextString().split(" ")[0]);
							break;
						case "documentName":
							est.setNombre(jr.nextString());
							break;
						case "territorycode":
							est.setCod_prov(jr.nextString().split(" ")[0]);
							break;
						default:
							jr.nextString();
						};
					}
					lhmMun.put(est.getNombre(), est);
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
				return lhmMun;
			}
		}
		return lhmMun;
	}

}
