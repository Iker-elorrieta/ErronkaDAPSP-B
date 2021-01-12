package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Municipio {
	
	private String cod_mun;
	private String nombre;
	private String cod_prov;
	public Municipio(String cod_mun, String nombre, String cod_prov) {
		super();
		this.cod_mun = cod_mun;
		this.nombre = nombre;
		this.cod_prov = cod_prov;
	}
	public Municipio() {
		super();
	}
	public String getCod_mun() {
		return cod_mun;
	}
	public void setCod_mun(String cod_mun) {
		this.cod_mun = cod_mun;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCod_prov() {
		return cod_prov;
	}
	public void setCod_prov(String cod_prov) {
		this.cod_prov = cod_prov;
	}
	@Override
	public String toString() {
		return "Municipio [cod_mun=" + cod_mun + ", nombre=" + nombre + ", cod_prov=" + cod_prov + "]";
	}
	public Node getElement(Document doc) {
		Element estazioa = doc.createElement("municipio");
		Element cod_mun = doc.createElement("cod_mun");
		cod_mun.setTextContent(this.cod_mun);
		estazioa.appendChild(cod_mun);
		Element nombre = doc.createElement("nombre");
		nombre.setTextContent(this.nombre);
		estazioa.appendChild(nombre);
		Element cod_prov = doc.createElement("cod_prov");
		cod_prov.setTextContent(this.cod_prov);
		estazioa.appendChild(cod_prov);
		return estazioa;
	}
	
	

}
