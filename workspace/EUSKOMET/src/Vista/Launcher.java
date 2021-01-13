package Vista;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Modelo.DatuKudeaketa.JSONIrakurri;
import Modelo.DatuKudeaketa.XMLIdatzi;
import Modelo.Objetuak.EspaciosNaturales;
import Modelo.Objetuak.Estazioa;
import Modelo.Objetuak.MunEspNa;
import Modelo.Objetuak.MunEst;
import Modelo.Objetuak.Municipio;

public class Launcher {

	public static void main(String[] args) {
		
		XMLIdatzi xmlidatzi = new XMLIdatzi();
		
		JSONIrakurri jsonirak = new JSONIrakurri();
		
		LinkedHashMap<String, Municipio> lhmMunicipio = jsonirak.herriaIrakurri();
		
		for (Municipio e : lhmMunicipio.values()) {
			System.out.println(e.toString());
		}
		
		xmlidatzi.XMLMunicipio(lhmMunicipio);
		
		Object[] o1 = jsonirak.estazioakIrakurri(lhmMunicipio);
		LinkedHashMap<String, Estazioa> lhmEstazioak = (LinkedHashMap<String, Estazioa>) o1[0];
		ArrayList<MunEst> alMunEst = (ArrayList<MunEst>) o1[1];
		for (Estazioa e : lhmEstazioak.values()) {
			if (e.getCod_mun() == 0) {
				System.err.println(e.toString());
			}
			else {
				System.out.println(e.toString());
			}
		}
		for (MunEst e : alMunEst) {
			System.out.println(e.toString());
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

	}

}
