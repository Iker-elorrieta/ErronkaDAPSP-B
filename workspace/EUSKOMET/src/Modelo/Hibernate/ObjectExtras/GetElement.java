package Modelo.Hibernate.ObjectExtras;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;

public class GetElement {
	
	public static Node getElement(Document doc, EspaciosNaturales espnat) {
		Element espazionat = doc.createElement("espacio_natural");
		Element cod_esp_natural = doc.createElement("codEspNatural");
		cod_esp_natural.setTextContent(String.valueOf(espnat.getCodEspNatural()));
		espazionat.appendChild(cod_esp_natural);
		Element nombre = doc.createElement("nombre");
		nombre.setTextContent(espnat.getNombre());
		espazionat.appendChild(nombre);
		Element tipo = doc.createElement("tipo");
		tipo.setTextContent(espnat.getTipo());
		espazionat.appendChild(tipo);
		Element longitud = doc.createElement("longitud");
		longitud.setTextContent(String.valueOf(espnat.getLongitud()));
		espazionat.appendChild(longitud);
		Element latitud = doc.createElement("latitud");
		latitud.setTextContent(String.valueOf(espnat.getLatitud()));
		espazionat.appendChild(latitud);
		return espazionat;
	}
	
	public static Node getElement(Document doc, MunEspNa munespna) {
		Element erlazioa = doc.createElement("mun_esp_na");
		Element codRelacion = doc.createElement("codRelacion");
		codRelacion.setTextContent(String.valueOf(munespna.getCodRelacion()));
		erlazioa.appendChild(codRelacion);
		Element cod_esp_na = doc.createElement("codEspNa");
		cod_esp_na.setTextContent(String.valueOf(munespna.getEspaciosNaturales().getCodEspNatural()));
		erlazioa.appendChild(cod_esp_na);
		Element cod_mun = doc.createElement("codMun");
		cod_mun.setTextContent(String.valueOf(munespna.getMunicipios().getCodMun()));
		erlazioa.appendChild(cod_mun);
		return erlazioa;
	}
	
	public static Node getElement(Document doc, Municipios mun) {
		Element municipio = doc.createElement("municipio");
		Element cod_mun = doc.createElement("codMun");
		cod_mun.setTextContent(String.valueOf(mun.getCodMun()));
		municipio.appendChild(cod_mun);
		Element nombre = doc.createElement("nombre");
		nombre.setTextContent(mun.getNombre());
		municipio.appendChild(nombre);
		Element cod_prov = doc.createElement("codProv");
		cod_prov.setTextContent(String.valueOf(mun.getProvincias().getCodProv()));
		municipio.appendChild(cod_prov);
		return municipio;
	}
	
	public static Node getElement(Document doc, Estaciones est) {
		Element estazioa = doc.createElement("estacion");
		Element id = doc.createElement("codEst");
		id.setTextContent(String.valueOf(est.getCodEst()));
		estazioa.appendChild(id);
		Element munucipio = doc.createElement("munucipio");
		munucipio.setTextContent(String.valueOf(est.getMunicipios().getCodMun()));
		estazioa.appendChild(munucipio);
		Element izena = doc.createElement("nombre");
		izena.setTextContent(est.getNombre());
		estazioa.appendChild(izena);
		Element herria = doc.createElement("pueblo");
		herria.setTextContent(est.getPueblo());
		estazioa.appendChild(herria);
		Element latitudea = doc.createElement("latitudea");
		latitudea.setTextContent(String.valueOf(est.getLatitud()));
		estazioa.appendChild(latitudea);
		Element longitudea = doc.createElement("longitudea");
		longitudea.setTextContent(String.valueOf(est.getLongitud()));
		estazioa.appendChild(longitudea);
		Element ICAEstacion = doc.createElement("ICAEstacion");
		ICAEstacion.setTextContent(est.getIcaEstacion());
		estazioa.appendChild(ICAEstacion);
		return estazioa;
	}

}
