package Modelo.Hibernate.Object;
// Generated 20 ene. 2021 14:03:01 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * EspaciosNaturales generated by hbm2java
 */
public class EspaciosNaturales implements java.io.Serializable {

	private int codEspNatural;
	private String nombre;
	private String tipo;
	private double latitud;
	private double longitud;
	private Set favoritosEsps = new HashSet(0);
	private Set fotosEspNaturaleses = new HashSet(0);
	private Set munEspNas = new HashSet(0);

	public EspaciosNaturales() {
	}

	public EspaciosNaturales(int codEspNatural, String nombre, String tipo, double latitud, double longitud) {
		this.codEspNatural = codEspNatural;
		this.nombre = nombre;
		this.tipo = tipo;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public EspaciosNaturales(int codEspNatural, String nombre, String tipo, double latitud, double longitud,
			Set favoritosEsps, Set fotosEspNaturaleses, Set munEspNas) {
		this.codEspNatural = codEspNatural;
		this.nombre = nombre;
		this.tipo = tipo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.favoritosEsps = favoritosEsps;
		this.fotosEspNaturaleses = fotosEspNaturaleses;
		this.munEspNas = munEspNas;
	}

	public int getCodEspNatural() {
		return this.codEspNatural;
	}

	public void setCodEspNatural(int codEspNatural) {
		this.codEspNatural = codEspNatural;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getLatitud() {
		return this.latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return this.longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public Set getFavoritosEsps() {
		return this.favoritosEsps;
	}

	public void setFavoritosEsps(Set favoritosEsps) {
		this.favoritosEsps = favoritosEsps;
	}

	public Set getFotosEspNaturaleses() {
		return this.fotosEspNaturaleses;
	}

	public void setFotosEspNaturaleses(Set fotosEspNaturaleses) {
		this.fotosEspNaturaleses = fotosEspNaturaleses;
	}

	public Set getMunEspNas() {
		return this.munEspNas;
	}

	public void setMunEspNas(Set munEspNas) {
		this.munEspNas = munEspNas;
	}

}
