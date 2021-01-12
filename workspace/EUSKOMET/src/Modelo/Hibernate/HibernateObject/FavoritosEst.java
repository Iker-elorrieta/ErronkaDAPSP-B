
package Modelo.Hibernate.HibernateObject;
// default package
// Generated 12 ene. 2021 18:50:39 by Hibernate Tools 5.4.21.Final

/**
 * FavoritosEst generated by hbm2java
 */
public class FavoritosEst implements java.io.Serializable {

	private FavoritosEstId id;
	private Estaciones estaciones;
	private Usuarios usuarios;

	public FavoritosEst() {
	}

	public FavoritosEst(FavoritosEstId id, Estaciones estaciones, Usuarios usuarios) {
		this.id = id;
		this.estaciones = estaciones;
		this.usuarios = usuarios;
	}

	public FavoritosEstId getId() {
		return this.id;
	}

	public void setId(FavoritosEstId id) {
		this.id = id;
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