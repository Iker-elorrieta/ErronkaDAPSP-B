package Modelo.Hibernate.Object;
// Generated 20 ene. 2021 8:58:11 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Estaciones generated by hbm2java
 */
public class Estaciones implements java.io.Serializable {

	private int codEst;
	private Municipios municipios;
	private String nombre;
	private String pueblo;
	private double latitud;
	private double longitud;
	private String icaEstacion;
	private Set favoritosEsts = new HashSet(0);
	private Set fotosEstacioneses = new HashSet(0);

	public Estaciones() {
	}

	public Estaciones(int codEst, Municipios municipios, String nombre, String pueblo, double latitud, double longitud,
			String icaEstacion) {
		this.codEst = codEst;
		this.municipios = municipios;
		this.nombre = nombre;
		this.pueblo = pueblo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.icaEstacion = icaEstacion;
	}

	public Estaciones(int codEst, Municipios municipios, String nombre, String pueblo, double latitud, double longitud,
			String icaEstacion, Set favoritosEsts, Set fotosEstacioneses) {
		this.codEst = codEst;
		this.municipios = municipios;
		this.nombre = nombre;
		this.pueblo = pueblo;
		this.latitud = latitud;
		this.longitud = longitud;
		this.icaEstacion = icaEstacion;
		this.favoritosEsts = favoritosEsts;
		this.fotosEstacioneses = fotosEstacioneses;
	}

	public int getCodEst() {
		return this.codEst;
	}

	public void setCodEst(int codEst) {
		this.codEst = codEst;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPueblo() {
		return this.pueblo;
	}

	public void setPueblo(String pueblo) {
		this.pueblo = pueblo;
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

	public String getIcaEstacion() {
		return this.icaEstacion;
	}

	public void setIcaEstacion(String icaEstacion) {
		this.icaEstacion = icaEstacion;
	}

	public Set getFavoritosEsts() {
		return this.favoritosEsts;
	}

	public void setFavoritosEsts(Set favoritosEsts) {
		this.favoritosEsts = favoritosEsts;
	}

	public Set getFotosEstacioneses() {
		return this.fotosEstacioneses;
	}

	public void setFotosEstacioneses(Set fotosEstacioneses) {
		this.fotosEstacioneses = fotosEstacioneses;
	}

}
