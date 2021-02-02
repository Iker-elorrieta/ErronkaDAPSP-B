package Modelo.Hibernate.Object;
// Generated 2 feb. 2021 9:19:47 by Hibernate Tools 5.4.21.Final

/**
 * FotosEspNaturales generated by hbm2java
 */
public class FotosEspNaturales implements java.io.Serializable {

	private Integer codFoto;
	private EspaciosNaturales espaciosNaturales;
	private int tam;
	private byte[] archivo;

	public FotosEspNaturales() {
	}

	public FotosEspNaturales(EspaciosNaturales espaciosNaturales, int tam, byte[] archivo) {
		this.espaciosNaturales = espaciosNaturales;
		this.tam = tam;
		this.archivo = archivo;
	}

	public Integer getCodFoto() {
		return this.codFoto;
	}

	public void setCodFoto(Integer codFoto) {
		this.codFoto = codFoto;
	}

	public EspaciosNaturales getEspaciosNaturales() {
		return this.espaciosNaturales;
	}

	public void setEspaciosNaturales(EspaciosNaturales espaciosNaturales) {
		this.espaciosNaturales = espaciosNaturales;
	}

	public int getTam() {
		return this.tam;
	}

	public void setTam(int tam) {
		this.tam = tam;
	}

	public byte[] getArchivo() {
		return this.archivo;
	}

	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}

}
