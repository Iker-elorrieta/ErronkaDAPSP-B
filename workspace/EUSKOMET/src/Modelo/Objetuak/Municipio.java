package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Municipio {
	
	private int cod_mun;
	private String nombre;
	private int cod_prov;
	public Municipio(int cod_mun, String nombre, int cod_prov) {
		super();
		this.cod_mun = cod_mun;
		this.nombre = nombre;
		this.cod_prov = cod_prov;
	}
	public Municipio() {
		super();
	}
	public int getCod_mun() {
		return cod_mun;
	}
	public void setCod_mun(int cod_mun) {
		this.cod_mun = cod_mun;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCod_prov() {
		return cod_prov;
	}
	public void setCod_prov(int cod_prov) {
		this.cod_prov = cod_prov;
	}
	@Override
	public String toString() {
		return "Municipio [cod_mun=" + cod_mun + ", nombre=" + nombre + ", cod_prov=" + cod_prov + "]";
	}
	public Node getElement(Document doc) {
		Element estazioa = doc.createElement("municipio");
		Element cod_mun = doc.createElement("cod_mun");
		cod_mun.setTextContent(String.valueOf(this.cod_mun));
		estazioa.appendChild(cod_mun);
		Element nombre = doc.createElement("nombre");
		nombre.setTextContent(this.nombre);
		estazioa.appendChild(nombre);
		Element cod_prov = doc.createElement("cod_prov");
		cod_prov.setTextContent(String.valueOf(this.cod_prov));
		estazioa.appendChild(cod_prov);
		return estazioa;
	}
	
	

}
