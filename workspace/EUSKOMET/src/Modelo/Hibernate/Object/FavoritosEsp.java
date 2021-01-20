package Modelo.Hibernate.Object;
// Generated 20 ene. 2021 8:58:11 by Hibernate Tools 5.4.21.Final

/**
 * FavoritosEsp generated by hbm2java
 */
public class FavoritosEsp implements java.io.Serializable {

	private String codRelacion;
	private EspaciosNaturales espaciosNaturales;
	private Usuarios usuarios;

	public FavoritosEsp() {
	}

	public FavoritosEsp(String codRelacion, EspaciosNaturales espaciosNaturales, Usuarios usuarios) {
		this.codRelacion = codRelacion;
		this.espaciosNaturales = espaciosNaturales;
		this.usuarios = usuarios;
	}

	public String getCodRelacion() {
		return this.codRelacion;
	}

	public void setCodRelacion(String codRelacion) {
		this.codRelacion = codRelacion;
	}

	public EspaciosNaturales getEspaciosNaturales() {
		return this.espaciosNaturales;
	}

	public void setEspaciosNaturales(EspaciosNaturales espaciosNaturales) {
		this.espaciosNaturales = espaciosNaturales;
	}

	public Usuarios getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(Usuarios usuarios) {
		this.usuarios = usuarios;
	}

}
