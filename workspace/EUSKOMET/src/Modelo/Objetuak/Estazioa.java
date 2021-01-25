package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Estazioa {

	private int id;
	private String izena;
	private String herria;
	private String latitudea;
	private String longitudea;
	private String ICAEstacion;
	private int cod_mun;

	public Estazioa(int id, String izena, String herria, String latitudea, String longitudea, String ICAEstacion, int cod_mun) {
		super();
		this.id = id;
		this.izena = izena;
		this.herria = herria;
		this.latitudea = latitudea;
		this.longitudea = longitudea;
		this.ICAEstacion = ICAEstacion;
		this.cod_mun = cod_mun;
	}

	public Estazioa() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getHerria() {
		return herria;
	}

	public void setHerria(String herria) {
		this.herria = herria;
	}

	public String getLatitudea() {
		return latitudea;
	}

	public void setLatitudea(String latitudea) {
		this.latitudea = latitudea;
	}

	public String getLongitudea() {
		return longitudea;
	}

	public void setLongitudea(String longitudea) {
		this.longitudea = longitudea;
	}

	public String getICAEstacion() {
		return ICAEstacion;
	}

	public void setICAEstacion(String iCAEstacion) {
		ICAEstacion = iCAEstacion;
	}

	public int getCod_mun() {
		return cod_mun;
	}

	public void setCod_mun(int cod_mun) {
		this.cod_mun = cod_mun;
	}

	@Override
	public String toString() {
		return "Estazioa [id=" + id + ", izena=" + izena + ", herria=" + herria + ", latitudea=" + latitudea
				+ ", longitudea=" + longitudea + ", ICAEstacion=" + ICAEstacion + ", cod_mun=" + cod_mun + "]";
	}

	public Node getElement(Document doc) {
		Element estazioa = doc.createElement("estazioa");
		Element id = doc.createElement("id");
		id.setTextContent(String.valueOf(this.id));
		estazioa.appendChild(id);
		Element izena = doc.createElement("izena");
		izena.setTextContent(this.izena);
		estazioa.appendChild(izena);
		Element herria = doc.createElement("herria");
		herria.setTextContent(this.herria);
		estazioa.appendChild(herria);
		Element latitudea = doc.createElement("latitudea");
		latitudea.setTextContent(this.latitudea);
		estazioa.appendChild(latitudea);
		Element longitudea = doc.createElement("longitudea");
		longitudea.setTextContent(this.longitudea);
		estazioa.appendChild(longitudea);
		Element ICAEstacion = doc.createElement("ICAEstacion");
		ICAEstacion.setTextContent(this.ICAEstacion);
		estazioa.appendChild(ICAEstacion);
		Element cod_mun = doc.createElement("cod_mun");
		cod_mun.setTextContent(String.valueOf(this.cod_mun));
		estazioa.appendChild(cod_mun);
		return estazioa;
	}

}
