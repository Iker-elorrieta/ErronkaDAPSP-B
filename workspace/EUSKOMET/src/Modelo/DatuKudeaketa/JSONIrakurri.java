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
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import org.jsoup.Jsoup;

import com.google.gson.stream.JsonReader;

import Modelo.Hibernate.Delete;
import Modelo.Hibernate.Insert;
import Modelo.Hibernate.Select;
import Modelo.Hibernate.Update;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.Hashes;
import Modelo.Hibernate.Object.Historico;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;

public class JSONIrakurri {

	public static String getHash(File f) {
		MessageDigest md;
		String txt2 = "";
		try {
			md = MessageDigest.getInstance("SHA");
			byte dataBytes[] = Files.readAllBytes(f.toPath());
			md.update(dataBytes);
			byte resum[] = md.digest();
			for (byte b : resum) {
				txt2 += b;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("[getHash] -- " + txt2);
		return txt2;
	}
	
	public static Date getDate(String fecha, String hora) {
		
		String[] str = fecha.split("/");
		int dia = Integer.parseInt(str[0]);
		int mes = Integer.parseInt(str[1]);
		int año = Integer.parseInt(str[2]);
		
		str = hora.split(":");
		int hor = Integer.parseInt(str[0]);
		hor = (hor == 24 ? 0 : hor);
		int min = Integer.parseInt(str[1]);
		
		LocalDate ld = LocalDate.of(año, mes, dia);
		
		Date date = Date.from(ld.atTime(hor, min, 0).atZone(ZoneId.systemDefault()).toInstant());
		
		Date semana = Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		if (date.before(semana)) {
			return null;
		}
		
		return date;
		
	}

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
		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/estaciones.json";
		File file = JSONDeskargatuFixed(url);
		Estaciones est;
		JsonReader jr;
		if (!Select.existeHash(url).equals(getHash(file))) {
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
							int cod;
							cod = Select.existeEstacion(est.getNombre());
							if (cod == -1) {
								cod = Select.obtCodEstacion();
								est.setCodEst(cod);
								est.setMunicipios(erlazioMunEst(lhmMunicipio, est.getPueblo(), est.getCodEst()));
								est.setIcaEstacion("Sin datos / Daturik gabe");
								Insert.insertar(est);
								est.setIcaEstacion(kalitateaIrakurri(est));
								Update.actualizar(est);
							}
							else {
								est.setCodEst(cod);
								est.setMunicipios(erlazioMunEst(lhmMunicipio, est.getPueblo(), est.getCodEst()));
								est.setIcaEstacion(kalitateaIrakurri(est));
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
		}
		else {
			lhmEstazioa = Select.obtEstaciones();
			for (Estaciones es : lhmEstazioa.values()) {
				es.setIcaEstacion(kalitateaIrakurri(es));
				Update.actualizar(es);
			}
		}
		if (Select.existeHash(url).equals("")) {
			Insert.insertar(new Hashes(url, getHash(file)));
		}
		else {
			Update.actualizar(new Hashes(url, getHash(file)));
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

	@SuppressWarnings("unused")
	public Object[] espazioNaturalakIrakurri(LinkedHashMap<Integer, Municipios> lhmMunicipio) {

		LinkedHashMap<Integer, EspaciosNaturales> lhmEspNat = new LinkedHashMap<Integer, EspaciosNaturales>();
		ArrayList<MunEspNa> alMunEsp = new ArrayList<MunEspNa>();
		String url = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/playas_de_euskadi/opendata/espacios-naturales.json";
		File file = JSONDeskargatuFixed(url);
		EspaciosNaturales est;
		JsonReader jr;
		String mun = null;
		String muncod = null;
		if (!Select.existeHash(url).equals(getHash(file))) {
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
					lhmEspNat.put(est.getCodEspNatural(), est);
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
				return new Object[] {lhmEspNat, alMunEsp};
			}
		}
		}
		else {
			lhmEspNat = Select.obtEspNaturales();
			for (MunEspNa men : Select.obtMunEspNats().values()) {
				alMunEsp.add(men);
			}
		}
		if (Select.existeHash(url).equals("")) {
			Insert.insertar(new Hashes(url, getHash(file)));
		}
		else {
			Update.actualizar(new Hashes(url, getHash(file)));
		}
		return new Object[] {lhmEspNat, alMunEsp};
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
		String url = "https://opendata.euskadi.eus/contenidos/ds_recursos_turisticos/pueblos_euskadi_turismo/opendata/herriak.json";
		File file = JSONDeskargatuFixed(url);
		Municipios est;
		JsonReader jr;
		if (!Select.existeHash(url).equals(getHash(file))) {
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
		}
		else {
			lhmMun = Select.obtMunicipios();
		}
		if (Select.existeHash(url).equals("")) {
			Insert.insertar(new Hashes(url, getHash(file)));
		}
		else {
			Update.actualizar(new Hashes(url, getHash(file)));
		}
		return lhmMun;
	}

	public String kalitateaIrakurri(Estaciones est) {
		String kalit = "";
		String nom = est.getNombre();
		nom = nom.replace(" ", "_");
		nom = nom.replace("(", "");
		nom = nom.replace(")", "");
		nom = nom.replace("Ñ", "N");
		nom = nom.replace("ñ", "n");
		nom = nom.replace(".", "");
		nom = nom.replace("ª", "");
		String url = "https://opendata.euskadi.eus/contenidos/ds_informes_estudios/calidad_aire_2021/es_def/adjuntos/datos_indice/"+ nom +".json";
		File file = JSONDeskargatuFixed(url);
		JsonReader jr;
		if (!Select.existeHash(url).equals(getHash(file))) {
		try {
			jr = new JsonReader(new FileReader(file, StandardCharsets.UTF_8));
			jr.setLenient(true);
			Delete.borrarHistoricosViejos(est);
			while(true) {
				jr.beginArray();
				while (jr.hasNext()) {
					String fecha = "";
					String hora = "";
					jr.beginObject();
					while (jr.hasNext()) {
						switch (jr.nextName()) {
						case "Date":
							fecha = jr.nextString();
							break;
						case "Hour":
							hora = jr.nextString();
							break;
						case "ICAEstacion":
							String ica = jr.nextString();
							if (kalit.equals("") || kalit.equals("Sin datos / Daturik gabe")) {
								kalit = ica;
							}
							else {
								if (!ica.equals("Sin datos / Daturik gabe")) {
									Date date = getDate(fecha, hora);
									if (date != null) Insert.insertar(new Historico(est, date, ica));
								}
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
		}
		else {
			kalit = est.getIcaEstacion();
		}
		if (Select.existeHash(url).equals("")) {
			Insert.insertar(new Hashes(url, getHash(file)));
		}
		else {
			Update.actualizar(new Hashes(url, getHash(file)));
		}
		return kalit;
	}

	public static String htmlGarbitu(String html) { 
		return Jsoup.parse(html).text(); 
	}

}
