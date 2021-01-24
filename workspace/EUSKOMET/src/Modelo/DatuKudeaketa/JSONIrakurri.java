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
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;

import com.google.gson.stream.JsonReader;

import Modelo.Hibernate.Insert;
import Modelo.Hibernate.Select;
import Modelo.Hibernate.Update;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;

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

	public LinkedHashMap<Integer, Estaciones> estazioakIrakurri(LinkedHashMap<Integer, Municipios> lhmMunicipio) {

		//LinkedHashMap<String, String> lhmEstekak = estekakIrakurri();
		LinkedHashMap<Integer, Estaciones> lhmEstazioa = new LinkedHashMap<Integer, Estaciones>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json");
		Estaciones est;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				todo:
					while (jr.hasNext()) {
						jr.beginObject();
						est = new Estaciones();
						try {
							while (jr.hasNext()) {
								switch (jr.nextName()) {
								case "Name":
									est.setNombre(jr.nextString());
									break;
								case "Town":
									est.setPueblo(jr.nextString());
//									if (est.getPueblo().equals("Amorebieta-Etxano"))
//										est.setPueblo("Zornotza");
									break;
								case "Latitude":
									est.setLatitud(Double.parseDouble(jr.nextString().replace(",", ".")));
									break;
								case "Longitude":
									est.setLongitud(Double.parseDouble(jr.nextString().replace(",", ".")));
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
						if (est.getNombre() != null) {
							String s = est.getNombre();
							s = s.replace(" ", "_");
							s = s.replace("(", "");
							s = s.replace(")", "");
							s = s.replace("Ñ", "N");
							s = s.replace("ñ", "n");
							s = s.replace(".", "");
							s = s.replace("ª", "");
							est.setIcaEstacion(kalitateaIrakurri("https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/datos_indice/"+ s +".json"  /*lhmEstekak.get(est.getIzena())*/));
						}
						int cod;
						cod = Select.existeEstacion(est.getNombre());
						if (cod == -1) {
							cod = Select.obtCodEstacion();
							est.setCodEst(cod);
							est.setMunicipios(erlazioMunEst(lhmMunicipio, est.getPueblo(), est.getCodEst()));
							Insert.insertar(est);
						}
						else {
							est.setCodEst(cod);
							est.setMunicipios(erlazioMunEst(lhmMunicipio, est.getPueblo(), est.getCodEst()));
							Update.actualizar(est);
						}
						lhmEstazioa.put(est.getCodEst(), est);
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

	private Municipios erlazioMunEst(LinkedHashMap<Integer, Municipios> lhmMunicipio, String mun, int codesp) {
		for (Municipios m : lhmMunicipio.values()) {
			if (mun.contains(m.getNombre()) || m.getNombre().contains(mun)) {
				return m;
			}
		}
		return null;
	}

	public Object[] espazioNaturalakIrakurri(LinkedHashMap<Integer, Municipios> lhmMunicipio) {

		LinkedHashMap<Integer, EspaciosNaturales> lhmEstazioa = new LinkedHashMap<Integer, EspaciosNaturales>();
		ArrayList<MunEspNa> alMunEsp = new ArrayList<MunEspNa>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json");
		EspaciosNaturales est;
		JsonReader jr;
		String mun = null;
		String muncod = null;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					boolean largo = false;
					est = new EspaciosNaturales();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "documentName":
							est.setNombre(jr.nextString());
							break;
						case "turismDescription":
							if (!largo) {
								jr.nextString();
								largo = true;
							}
							else {
								est.setDescripcion(htmlGarbitu(jr.nextString()));
							}
							break;
						case "natureType":
							est.setTipo(jr.nextString());
							break;
						case "lonwgs84":
							String s1 = jr.nextString().replace(",", ".");
							est.setLongitud((s1.equals("") ? 0 : Double.parseDouble(s1)));
							break;
						case "latwgs84":
							String s2 = jr.nextString().replace(",", ".");
							est.setLatitud((s2.equals("") ? 0 : Double.parseDouble(s2)));
							break;
						case "municipality":
							mun = jr.nextString();
							break;
						case "municipalitycode":
							muncod = jr.nextString();
							break;
						default:
							jr.nextString();
						};
					}
					int cod;
					cod = Select.existeEspNat(est.getNombre());
					if (cod == -1) {
						cod = Select.obtCodEspNat();
						est.setCodEspNatural(cod);
						Insert.insertar(est);
					}
					else {
						est.setCodEspNatural(cod);
						Update.actualizar(est);
					}
					if (mun.contains("Trapagaran")) {
						mun = "Valle_de_Trápaga-Trapagaran";
					} else if (mun.contains("Donostia")) {
						mun = "Donostia/San_Sebastián";
					}
					erlazioMunEsp(lhmMunicipio, mun, est, alMunEsp);
					lhmEstazioa.put(est.getCodEspNatural(), est);
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

	private void erlazioMunEsp(LinkedHashMap<Integer, Municipios> lhmMunicipio, String mun, EspaciosNaturales esp, ArrayList<MunEspNa> alMunEsp) {
		for (Municipios m : lhmMunicipio.values()) {
			boolean esta = false;
			for (String s : mun.split(" ")) {
				if (s.equals(m.getNombre())) {
					esta = true;
				}
			}
			if (esta) {
			MunEspNa e = new MunEspNa(m.getCodMun() + "-" + esp.getCodEspNatural(), esp, m);
			alMunEsp.add(e);
			if (!Select.existeMunEspNa(e.getCodRelacion()))
					Insert.insertar(e);
		}
		}
	}

	public LinkedHashMap<Integer, Municipios> herriaIrakurri() {
		LinkedHashMap<Integer, Municipios> lhmMun = new LinkedHashMap<Integer, Municipios>();
		File file = JSONDeskargatuFixed("https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json");
		Municipios est;
		JsonReader jr;
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					jr.beginObject();
					est = new Municipios();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "municipality":
							String nombre = jr.nextString();
							if (nombre.contains("Trapagaran")) {
								nombre = "Valle_de_Trápaga-Trapagaran";
							} else if (nombre.contains("Donostia")) {
								nombre = "Donostia/San_Sebastián";
							} else if (nombre.indexOf(" ") != -1) {
								nombre = nombre.substring(0, nombre.indexOf(" "));
							}
							est.setNombre(nombre);
							break;
						case "turismDescription":
							est.setDescripcion(htmlGarbitu(jr.nextString()));
							break;
						case "territorycode":
							est.setProvincias(Select.obtProvincias().get(Integer.parseInt(jr.nextString().split(" ")[0])));
							break;
						default:
							jr.nextString();
						};
					}
					int cod;
					cod = Select.existeMunicipio(est.getNombre());
					if (cod == -1) {
						cod = Select.obtCodMunicipio();
						est.setCodMun(cod);
						Insert.insertar(est);
					}
					else {
						est.setCodMun(cod);
						Update.actualizar(est);
					}
					lhmMun.put(est.getCodMun(), est);
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
	
	public static String htmlGarbitu(String html) { 
		return Jsoup.parse(html).text(); 
	}

}
