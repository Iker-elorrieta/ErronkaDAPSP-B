package Modelo.Hibernate.Object;
// Generated 29 ene. 2021 8:27:12 by Hibernate Tools 5.4.21.Final

/**
 * FavoritosMun generated by hbm2java
 */
public class FavoritosMun implements java.io.Serializable {

	private String codRelacion;
	private Municipios municipios;
	private Usuarios usuarios;

	public FavoritosMun() {
	}

	public FavoritosMun(String codRelacion, Municipios municipios, Usuarios usuarios) {
		this.codRelacion = codRelacion;
		this.municipios = municipios;
		this.usuarios = usuarios;
	}

	public String getCodRelacion() {
		return this.codRelacion;
	}

	public void setCodRelacion(String codRelacion) {
		this.codRelacion = codRelacion;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
