package Modelo.Hibernate.Object;
// Generated 24 ene. 2021 15:49:15 by Hibernate Tools 5.4.21.Final

/**
 * FavoritosEst generated by hbm2java
 */
public class FavoritosEst implements java.io.Serializable {

	private String codRelacion;
	private Estaciones estaciones;
	private Usuarios usuarios;

	public FavoritosEst() {
	}

	public FavoritosEst(String codRelacion, Estaciones estaciones, Usuarios usuarios) {
		this.codRelacion = codRelacion;
		this.estaciones = estaciones;
		this.usuarios = usuarios;
	}

	public String getCodRelacion() {
		return this.codRelacion;
	}

	public void setCodRelacion(String codRelacion) {
		this.codRelacion = codRelacion;
	}

	public Estaciones getEstaciones() {
		return this.estaciones;
	}

	public void setEstaciones(Estaciones estaciones) {
		this.estaciones = estaciones;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
