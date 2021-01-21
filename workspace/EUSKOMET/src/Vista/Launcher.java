package Vista;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import Modelo.DatuKudeaketa.JSONIrakurri;
import Modelo.DatuKudeaketa.XMLIdatzi;
import Modelo.Hibernate.Insert;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.ObjectExtras.ToString;

public class Launcher {

	public static void main(String[] args) {
		
		ejecutar();

	}

	public static int ejecutar() {
		int estado = 1;
		XMLIdatzi xmlidatzi = new XMLIdatzi();
		
		JSONIrakurri jsonirak = new JSONIrakurri();
		
		LinkedHashMap<Integer, Municipios> lhmMunicipio = jsonirak.herriaIrakurri();
		
		for (Municipios e : lhmMunicipio.values()) {
			System.out.println(ToString.toString(e));
		}
		
		xmlidatzi.XMLMunicipio(lhmMunicipio);
		
		LinkedHashMap<Integer, Estaciones> lhmEstazioak = jsonirak.estazioakIrakurri(lhmMunicipio);
		for (Estaciones e : lhmEstazioak.values()) {
			System.out.println(ToString.toString(e));
		}
		
		xmlidatzi.XMLEstazioak(lhmEstazioak);
		
		Object[] o = jsonirak.espazioNaturalakIrakurri(lhmMunicipio);
		LinkedHashMap<Integer, EspaciosNaturales> lhmEspNat = (LinkedHashMap<Integer, EspaciosNaturales>) o[0];
		ArrayList<MunEspNa> alMunEsp = (ArrayList<MunEspNa>) o[1];
		
		for (EspaciosNaturales e : lhmEspNat.values()) {
			System.out.println(ToString.toString(e));
		}
		
		xmlidatzi.XMLEspazioNaturalak(lhmEspNat);
		
		for (MunEspNa e : alMunEsp) {
			System.out.println(ToString.toString(e));
		}
		
		xmlidatzi.XMLMunEspNa(alMunEsp);
		
		estado = 0;
		return estado;
	}

}
