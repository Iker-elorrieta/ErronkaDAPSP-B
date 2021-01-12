package Vista;

import java.util.LinkedHashMap;

import Modelo.DatuKudeaketa.JSONIrakurri;
import Modelo.DatuKudeaketa.XMLIdatzi;
import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.Municipio;

public class Launcher {

	public static void main(String[] args) {
		
		JSONIrakurri jsonirak = new JSONIrakurri();
		
		LinkedHashMap<String, Estazioa> lhmEstazioak = jsonirak.estazioakIrakurri();
		
		for (Estazioa e : lhmEstazioak.values()) {
			System.out.println(e.toString());
		}
		
		XMLIdatzi xmlidatzi = new XMLIdatzi();
		
		xmlidatzi.XMLEstazioak(lhmEstazioak);
		
		LinkedHashMap<String, EspaciosNaturales> lhmEspNat = jsonirak.espazioNaturalakIrakurri();
		
		for (EspaciosNaturales e : lhmEspNat.values()) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLEspazioNaturalak(lhmEspNat);
		
		LinkedHashMap<String, Municipio> lhmMunicipio = jsonirak.herriaIrakurri();
		
		for (Municipio e : lhmMunicipio.values()) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLMunicipio(lhmMunicipio);

	}

}
