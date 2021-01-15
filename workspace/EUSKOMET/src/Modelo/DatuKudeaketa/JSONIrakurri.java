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
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import com.google.gson.stream.JsonReader;

import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.MunEspNa;
import Modelo.Objetuak.Municipio;

public class JSONIrakurri {

	private File JSONDeskargatuFixed(String url) {

		String name = "temp.json";
		String folder = "Descargas/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return null;
		File file = new File(folder + (url.equals("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json") ? "estaciones.json" : name));
		if (file.exists()) {
			file.delete();
		}
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

	public File JSONDeskargatu(String url) {

		String name = "temp.json";
		String folder = "Descargas/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return null;
		File file = new File(folder + name);
		URLConnection conn = null;
		try {
			System.out.println(url);
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
			return null;
		}
		return file;
	}

	public LinkedHashMap<String, Estazioa> estazioakIrakurri(LinkedHashMap<String, Municipio> lhmMunicipio) {

		//LinkedHashMap<String, String> lhmEstekak = estekakIrakurri();
		LinkedHashMap<String, Estazioa> lhmEstazioa = new LinkedHashMap<String, Estazioa>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json");
		Estazioa est;
		JsonReader jr;
		int cod = 1;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				todo:
				while (jr.hasNext()) {
					jr.beginObject();
					est = new Estazioa();
					est.setId(cod++);
					try {
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "Name":
							est.setIzena(jr.nextString());
							System.out.println(est.getIzena());
							break;
						case "Town":
							est.setHerria(jr.nextString());
							if (est.getHerria().equals("Amorebieta-Etxano"))
								est.setHerria("Zornotza");
							est.setCod_mun(erlazioMunEst(lhmMunicipio, est.getHerria(), est.getId()));
							break;
						case "Latitude":
							est.setLatitudea(jr.nextString());
							break;
						case "Longitude":
							est.setLongitudea(jr.nextString());
							break;
						default:
							jr.nextString();
						};
					}
					}
					catch (Exception e) {
						if (!e.getMessage().contains("Unterminated object at line")) {
							e.printStackTrace();
						}
						else {
							jr.skipValue();
							jr.skipValue();
							jr.endObject();
							continue todo;
						}
					}
					if (est.getIzena() != null) {
					String s = est.getIzena();
					s = s.replace(" ", "_");
					s = s.replace("(", "");
					s = s.replace(")", "");
					s = s.replace("Ñ", "N");
					s = s.replace("ñ", "n");
					s = s.replace(".", "");
					s = s.replace("ª", "");
					est.setICAEstacion(kalitateaIrakurri("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/datos_indice/"+ s +".json"  /*lhmEstekak.get(est.getIzena())*/));
					}
					lhmEstazioa.put((est.getIzena() == null ? "fallo-" + new Date().toInstant() : est.getIzena()), est);
					jr.endObject();
				}
				jr.endArray();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			if (!e.getMessage().contains("Expected BEGIN_ARRAY but was END_DOCUMENT") && !e.getMessage().contains("Expected BEGIN_OBJECT but was END_DOCUMENT")) {
				e.printStackTrace();
			}
			else {
				return lhmEstazioa;
			}
		}
		return lhmEstazioa;
	}

	private int erlazioMunEst(LinkedHashMap<String, Municipio> lhmMunicipio, String mun, int codesp) {
		for (Municipio m : lhmMunicipio.values()) {
			if (mun.contains(m.getNombre()) || m.getNombre().contains(mun)) {
				return m.getCod_mun();
			}
		}
		return 0;
	}

	public Object[] espazioNaturalakIrakurri(LinkedHashMap<String, Municipio> lhmMunicipio) {

		LinkedHashMap<String, EspaciosNaturales> lhmEstazioa = new LinkedHashMap<String, EspaciosNaturales>();
		ArrayList<MunEspNa> alMunEsp = new ArrayList<MunEspNa>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json");
		EspaciosNaturales est;
		JsonReader jr;
		int cod = 1;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new EspaciosNaturales();
					est.setCod_esp_natural(cod++);
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
						case "municipality":
							String mun = jr.nextString();
							erlazioMunEsp(lhmMunicipio, mun, est.getCod_esp_natural(), alMunEsp);
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
			if (!e.getMessage().contains("Expected BEGIN_ARRAY but was END_DOCUMENT") && !e.getMessage().contains("Expected BEGIN_OBJECT but was END_DOCUMENT")) {
				e.printStackTrace();
			}
			else {
				return new Object[] {lhmEstazioa, alMunEsp};
			}
		}
		return new Object[] {lhmEstazioa, alMunEsp};
	}

	private void erlazioMunEsp(LinkedHashMap<String, Municipio> lhmMunicipio, String mun, int codesp, ArrayList<MunEspNa> alMunEsp) {
		for (Municipio m : lhmMunicipio.values()) {
			if (mun.contains(m.getNombre()) || m.getNombre().contains(mun)) {
				alMunEsp.add(new MunEspNa(codesp, m.getCod_mun()));
			}
		}
	}

	public LinkedHashMap<String, Municipio> herriaIrakurri() {
		LinkedHashMap<String, Municipio> lhmMun = new LinkedHashMap<String, Municipio>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json");
		Municipio est;
		int cod = 1;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new Municipio();
					est.setCod_mun(cod++);
					while (jr.hasNext()) {
						switch (jr.nextName()) {
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
			if (!e.getMessage().contains("Expected BEGIN_ARRAY but was END_DOCUMENT") && !e.getMessage().contains("Expected BEGIN_OBJECT but was END_DOCUMENT")) {
				e.printStackTrace();
			}
			else {
				return lhmMun;
			}
		}
		return lhmMun;
	}

	public String kalitateaIrakurri(String url) {
		String kalit = "";
		File file = JSONDeskargatuFixed(url);
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "ICAEstacion":
							if (kalit.equals("") || kalit.equals("Sin datos / Daturik gabe")) {
								kalit = jr.nextString();
							}
							else {
								jr.nextString();
							}
							break;
						default:
							jr.nextString();
						};
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
			if (!e.getMessage().contains("Expected BEGIN_ARRAY but was END_DOCUMENT") && !e.getMessage().contains("Expected BEGIN_OBJECT but was END_DOCUMENT")) {
				e.printStackTrace();
			}
			else {
				return kalit;
			}
		}
		return kalit;
	}

}
