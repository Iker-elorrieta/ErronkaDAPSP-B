package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class Estazioa {

	private int id;
	private String izena;
	private String probintzia;
	private String herria;
	private String helbidea;
	private String koordenatuakX;
	private String koordenatuakY;
	private String latitudea;
	private String longitudea;

	public Estazioa(int id, String izena, String probintzia, String herria, String helbidea, String koordenatuakX,
			String koordenatuakY, String latitudea, String longitudea) {
		super();
		this.id = id;
		this.izena = izena;
		this.probintzia = probintzia;
		this.herria = herria;
		this.helbidea = helbidea;
		this.koordenatuakX = koordenatuakX;
		this.koordenatuakY = koordenatuakY;
		this.latitudea = latitudea;
		this.longitudea = longitudea;
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

	public String getProbintzia() {
		return probintzia;
	}

	public void setProbintzia(String probintzia) {
		this.probintzia = probintzia;
	}

	public String getHerria() {
		return herria;
	}

	public void setHerria(String herria) {
		this.herria = herria;
	}

	public String getHelbidea() {
		return helbidea;
	}

	public void setHelbidea(String helbidea) {
		this.helbidea = helbidea;
	}

	public String getKoordenatuakX() {
		return koordenatuakX;
	}

	public void setKoordenatuakX(String koordenatuakX) {
		this.koordenatuakX = koordenatuakX;
	}

	public String getKoordenatuakY() {
		return koordenatuakY;
	}

	public void setKoordenatuakY(String koordenatuakY) {
		this.koordenatuakY = koordenatuakY;
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

	@Override
	public String toString() {
		return "Estazioa [id=" + id + ", izena=" + izena + ", probintzia=" + probintzia + ", herria=" + herria
				+ ", helbidea=" + helbidea + ", koordenatuakX=" + koordenatuakX + ", koordenatuakY=" + koordenatuakY
				+ ", latitudea=" + latitudea + ", longitudea=" + longitudea + "]";
	}

	public Node getElement(Document doc) {
		Element estazioa = doc.createElement("estazioa");
		Element id = doc.createElement("id");
		id.setTextContent(String.valueOf(this.id));
		estazioa.appendChild(id);
		Element izena = doc.createElement("izena");
		izena.setTextContent(this.izena);
		estazioa.appendChild(izena);
		Element probintzia = doc.createElement("probintzia");
		probintzia.setTextContent(this.probintzia);
		estazioa.appendChild(probintzia);
		Element herria = doc.createElement("herria");
		herria.setTextContent(this.herria);
		estazioa.appendChild(herria);
		Element helbidea = doc.createElement("helbidea");
		helbidea.setTextContent(this.helbidea);
		estazioa.appendChild(helbidea);
		Element koordenatuakX = doc.createElement("koordenatuakX");
		koordenatuakX.setTextContent(this.koordenatuakX);
		estazioa.appendChild(koordenatuakX);
		Element koordenatuakY = doc.createElement("koordenatuakY");
		koordenatuakY.setTextContent(this.koordenatuakY);
		estazioa.appendChild(koordenatuakY);
		Element latitudea = doc.createElement("latitudea");
		latitudea.setTextContent(this.latitudea);
		estazioa.appendChild(latitudea);
		Element longitudea = doc.createElement("longitudea");
		longitudea.setTextContent(this.longitudea);
		estazioa.appendChild(longitudea);
		return estazioa;
	}

}
