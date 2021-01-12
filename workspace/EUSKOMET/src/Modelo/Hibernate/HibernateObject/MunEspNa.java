package Modelo.Hibernate.HibernateObject;
// default package
// Generated 12 ene. 2021 18:50:39 by Hibernate Tools 5.4.21.Final

/**
 * MunEspNa generated by hbm2java
 */
public class MunEspNa implements java.io.Serializable {

	private MunEspNaId id;
	private EspaciosNaturales espaciosNaturales;
	private Municipios municipios;

	public MunEspNa() {
	}

	public MunEspNa(MunEspNaId id, EspaciosNaturales espaciosNaturales, Municipios municipios) {
		this.id = id;
		this.espaciosNaturales = espaciosNaturales;
		this.municipios = municipios;
	}

	public MunEspNaId getId() {
		return this.id;
	}

	public void setId(MunEspNaId id) {
		this.id = id;
	}

	public EspaciosNaturales getEspaciosNaturales() {
		return this.espaciosNaturales;
	}

	public void setEspaciosNaturales(EspaciosNaturales espaciosNaturales) {
		this.espaciosNaturales = espaciosNaturales;
	}

	public Municipios getMunicipios() {
		return this.municipios;
	}

	public void setMunicipios(Municipios municipios) {
		this.municipios = municipios;
	}

}
