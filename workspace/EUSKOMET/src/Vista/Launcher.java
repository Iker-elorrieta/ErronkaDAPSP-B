package Vista;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Modelo.DatuKudeaketa.JSONIrakurri;
import Modelo.DatuKudeaketa.XMLIdatzi;
import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.MunEspNa;
import Modelo.Objetuak.Municipio;

public class Launcher {

	public static void main(String[] args) {
		
		ejecutar();

	}

	public static int ejecutar() {
		int estado = 1;
		XMLIdatzi xmlidatzi = new XMLIdatzi();
		
		JSONIrakurri jsonirak = new JSONIrakurri();
		
		LinkedHashMap<String, Municipio> lhmMunicipio = jsonirak.herriaIrakurri();
		
		for (Municipio e : lhmMunicipio.values()) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLMunicipio(lhmMunicipio);
		
		LinkedHashMap<String, Estazioa> lhmEstazioak = jsonirak.estazioakIrakurri(lhmMunicipio);
		for (Estazioa e : lhmEstazioak.values()) {
			if (e.getCod_mun() == 0) {
				System.err.println(e.toString());
			}
			else {
				System.out.println(e.toString());
			}
		}
		
		xmlidatzi.XMLEstazioak(lhmEstazioak);
		
		Object[] o = jsonirak.espazioNaturalakIrakurri(lhmMunicipio);
		LinkedHashMap<String, EspaciosNaturales> lhmEspNat = (LinkedHashMap<String, EspaciosNaturales>) o[0];
		ArrayList<MunEspNa> alMunEsp = (ArrayList<MunEspNa>) o[1];
		
		for (EspaciosNaturales e : lhmEspNat.values()) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLEspazioNaturalak(lhmEspNat);
		
		for (MunEspNa e : alMunEsp) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLMunEspNa(alMunEsp);
		
		estado = 0;
		return estado;
	}

}
