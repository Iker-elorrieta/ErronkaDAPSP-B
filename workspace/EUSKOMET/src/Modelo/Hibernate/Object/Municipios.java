package Modelo.Hibernate.Object;
// Generated 29 ene. 2021 8:27:12 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Municipios generated by hbm2java
 */
public class Municipios implements java.io.Serializable {

	private int codMun;
	private Provincias provincias;
	private String nombre;
	private String descripcion;
	private Set favoritosMuns = new HashSet(0);
	private Set fotosMunicipioses = new HashSet(0);
	private Set munEspNas = new HashSet(0);
	private Set estacioneses = new HashSet(0);

	public Municipios() {
	}

	public Municipios(int codMun, Provincias provincias, String nombre) {
		this.codMun = codMun;
		this.provincias = provincias;
		this.nombre = nombre;
	}

	public Municipios(int codMun, Provincias provincias, String nombre, String descripcion, Set favoritosMuns,
			Set fotosMunicipioses, Set munEspNas, Set estacioneses) {
		this.codMun = codMun;
		this.provincias = provincias;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.favoritosMuns = favoritosMuns;
		this.fotosMunicipioses = fotosMunicipioses;
		this.munEspNas = munEspNas;
		this.estacioneses = estacioneses;
	}

	public int getCodMun() {
		return this.codMun;
	}

	public void setCodMun(int codMun) {
		this.codMun = codMun;
	}

	public Provincias getProvincias() {
		return this.provincias;
	}

	public void setProvincias(Provincias provincias) {
		this.provincias = provincias;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getFavoritosMuns() {
		return this.favoritosMuns;
	}

	public void setFavoritosMuns(Set favoritosMuns) {
		this.favoritosMuns = favoritosMuns;
	}

	public Set getFotosMunicipioses() {
		return this.fotosMunicipioses;
	}

	public void setFotosMunicipioses(Set fotosMunicipioses) {
		this.fotosMunicipioses = fotosMunicipioses;
	}

	public Set getMunEspNas() {
		return this.munEspNas;
	}

	public void setMunEspNas(Set munEspNas) {
		this.munEspNas = munEspNas;
	}

	public Set getEstacioneses() {
		return this.estacioneses;
	}

	public void setEstacioneses(Set estacioneses) {
		this.estacioneses = estacioneses;
	}

}
