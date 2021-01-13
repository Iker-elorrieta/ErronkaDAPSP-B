package Modelo.Objetuak;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class MunEspNa {
	
	private int cod_esp_na;
	private int cod_mun;
	public MunEspNa(int cod_esp_na, int cod_mun) {
		super();
		this.cod_esp_na = cod_esp_na;
		this.cod_mun = cod_mun;
	}
	public MunEspNa() {
		super();
	}
	public int getCod_esp_na() {
		return cod_esp_na;
	}
	public void setCod_esp_na(int cod_esp_na) {
		this.cod_esp_na = cod_esp_na;
	}
	public int getCod_mun() {
		return cod_mun;
	}
	public void setCod_mun(int cod_mun) {
		this.cod_mun = cod_mun;
	}
	@Override
	public String toString() {
		return "Mun_esp_na [cod_esp_na=" + cod_esp_na + ", cod_mun=" + cod_mun + "]";
	}
	public Node getElement(Document doc) {
		Element erlazioa = doc.createElement("mun_esp_na");
		Element cod_esp_na = doc.createElement("cod_esp_na");
		cod_esp_na.setTextContent(String.valueOf(this.cod_esp_na));
		erlazioa.appendChild(cod_esp_na);
		Element cod_mun = doc.createElement("cod_mun");
		cod_mun.setTextContent(String.valueOf(this.cod_mun));
		erlazioa.appendChild(cod_mun);
		return erlazioa;
	}
	

}
