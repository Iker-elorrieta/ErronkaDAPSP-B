package Modelo.Objetuak;

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
	
}
