package Modelo.Hibernate.Object;
// Generated 24 ene. 2021 15:49:15 by Hibernate Tools 5.4.21.Final

/**
 * FotosEspNaturales generated by hbm2java
 */
public class FotosEspNaturales implements java.io.Serializable {

	private int codFoto;
	private EspaciosNaturales espaciosNaturales;
	private String archivo;

	public FotosEspNaturales() {
	}

	public FotosEspNaturales(int codFoto, EspaciosNaturales espaciosNaturales, String archivo) {
		this.codFoto = codFoto;
		this.espaciosNaturales = espaciosNaturales;
		this.archivo = archivo;
	}

	public int getCodFoto() {
		return this.codFoto;
	}

	public void setCodFoto(int codFoto) {
		this.codFoto = codFoto;
	}

	public EspaciosNaturales getEspaciosNaturales() {
		return this.espaciosNaturales;
	}

	public void setEspaciosNaturales(EspaciosNaturales espaciosNaturales) {
		this.espaciosNaturales = espaciosNaturales;
	}

	public String getArchivo() {
		return this.archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

}
