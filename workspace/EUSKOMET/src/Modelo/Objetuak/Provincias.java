package Modelo.Objetuak;

import java.io.Serializable;

public class Provincias implements Serializable {

	private int cod_prov;
	private String nombre;
	
	public Provincias(int cod_prov, String nombre) {
		super();
		this.cod_prov = cod_prov;
		this.nombre = nombre;
	}

	public int getCod_prov() {
		return cod_prov;
	}

	public void setCod_prov(int cod_prov) {
		this.cod_prov = cod_prov;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Provincias [cod_prov=" + cod_prov + ", nombre=" + nombre + "]";
	}
	
	
	
}
