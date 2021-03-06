package Modelo.Hibernate.Object;
// Generated 29 ene. 2021 8:27:12 by Hibernate Tools 5.4.21.Final

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

}
