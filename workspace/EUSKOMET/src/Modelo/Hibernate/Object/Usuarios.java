package Modelo.Hibernate.Object;
// Generated 24 ene. 2021 15:49:15 by Hibernate Tools 5.4.21.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Usuarios generated by hbm2java
 */
public class Usuarios implements java.io.Serializable {

	private int codUser;
	private String nombre;
	private String contra;
	private String pregunta;
	private String respuesta;
	private Set favoritosEsts = new HashSet(0);
	private Set favoritosEsps = new HashSet(0);

	public Usuarios() {
	}

	public Usuarios(int codUser, String nombre, String contra, String pregunta, String respuesta) {
		this.codUser = codUser;
		this.nombre = nombre;
		this.contra = contra;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
	}

	public Usuarios(int codUser, String nombre, String contra, String pregunta, String respuesta, Set favoritosEsts,
			Set favoritosEsps) {
		this.codUser = codUser;
		this.nombre = nombre;
		this.contra = contra;
		this.pregunta = pregunta;
		this.respuesta = respuesta;
		this.favoritosEsts = favoritosEsts;
		this.favoritosEsps = favoritosEsps;
	}

	public int getCodUser() {
		return this.codUser;
	}

	public void setCodUser(int codUser) {
		this.codUser = codUser;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContra() {
		return this.contra;
	}

	public void setContra(String contra) {
		this.contra = contra;
	}

	public String getPregunta() {
		return this.pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public String getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Set getFavoritosEsts() {
		return this.favoritosEsts;
	}

	public void setFavoritosEsts(Set favoritosEsts) {
		this.favoritosEsts = favoritosEsts;
	}

	public Set getFavoritosEsps() {
		return this.favoritosEsps;
	}

	public void setFavoritosEsps(Set favoritosEsps) {
		this.favoritosEsps = favoritosEsps;
	}

}
