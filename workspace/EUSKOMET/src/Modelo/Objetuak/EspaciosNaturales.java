package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class EspaciosNaturales {
	
	private int cod_esp_natural;
	private String nombre;
	private String tipo;
	private double longitud;
	private double latitud;
	private String cod_mun;
	public EspaciosNaturales(int cod_esp_natural, String nombre, String tipo, double longitud, double latitud,
			String cod_mun) {
		super();
		this.cod_esp_natural = cod_esp_natural;
		this.nombre = nombre;
		this.tipo = tipo;
		this.longitud = longitud;
		this.latitud = latitud;
		this.cod_mun = cod_mun;
	}
	public EspaciosNaturales() {
		super();
	}
	public int getCod_esp_natural() {
		return cod_esp_natural;
	}
	public void setCod_esp_natural(int cod_esp_natural) {
		this.cod_esp_natural = cod_esp_natural;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public String getCod_mun() {
		return cod_mun;
	}
	public void setCod_mun(String cod_mun) {
		this.cod_mun = cod_mun;
	}
	@Override
	public String toString() {
		return "EspaciosNaturales [cod_esp_natural=" + cod_esp_natural + ", nombre=" + nombre + ", tipo=" + tipo
				+ ", longitud=" + longitud + ", latitud=" + latitud + ", cod_mun=" + cod_mun + "]";
	}
	
	public Node getElement(Document doc) {
		Element espazionat = doc.createElement("espacio_naturale");
		Element cod_esp_natural = doc.createElement("cod_esp_natural");
		cod_esp_natural.setTextContent(String.valueOf(this.cod_esp_natural));
		espazionat.appendChild(cod_esp_natural);
		Element nombre = doc.createElement("nombre");
		nombre.setTextContent(this.nombre);
		espazionat.appendChild(nombre);
		Element tipo = doc.createElement("tipo");
		tipo.setTextContent(this.tipo);
		espazionat.appendChild(tipo);
		Element longitud = doc.createElement("longitud");
		longitud.setTextContent(String.valueOf(this.longitud));
		espazionat.appendChild(longitud);
		Element latitud = doc.createElement("latitud");
		latitud.setTextContent(String.valueOf(this.latitud));
		espazionat.appendChild(latitud);
		Element cod_mun = doc.createElement("cod_mun");
		cod_mun.setTextContent(String.valueOf(this.cod_mun));
		espazionat.appendChild(cod_mun);
		return espazionat;
	}

}
