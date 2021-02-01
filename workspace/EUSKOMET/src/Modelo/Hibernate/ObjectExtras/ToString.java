package Modelo.Hibernate.ObjectExtras;

import Modelo.Hibernate.Select;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;

public class ToString {

	public static String toString(EspaciosNaturales espnat, boolean mostrar_descripcion) {
		return "EspaciosNaturales [" + 
				"codEspNatural=" + espnat.getCodEspNatural() + 
				", nombre=" + espnat.getNombre() + 
				", tipo=" + espnat.getTipo() + 
				", latitud=" + espnat.getLatitud() + 
				", longitud=" + espnat.getLongitud() + 
				(mostrar_descripcion ? ", descripcion=" + espnat.getDescripcion() : "") +
				"]";
	}
	
	public static String toStringFormat(EspaciosNaturales espnat, boolean mostrar_descripcion) {
		return "ESPACIO NATURAL" + 
//				"\nCódigo:\t" + espnat.getCodEspNatural() + 
				"\nNombre:\t" + espnat.getNombre() + 
				"\nTipo:\t" + espnat.getTipo() + 
				"\nLatitud:\t" + espnat.getLatitud() + 
				"\nLongitud:\t" + espnat.getLongitud() + 
				(mostrar_descripcion ? "\nDescripcion:\t" + espnat.getDescripcion() : "") +
				"\n--------------------------------------------------------------------";
	}
	
	public static String toString(Estaciones est) {
		return "Estaciones [" + 
				"codEst=" + est.getCodEst() + 
				", municipios=" + est.getMunicipios().getCodMun() + 
				", nombre=" + est.getNombre() + 
				", pueblo=" + est.getPueblo() + 
				", latitud=" + est.getLatitud() + 
				", longitud=" + est.getLongitud() + 
				", icaEstacion=" + est.getIcaEstacion() + 
				"]";
	}
	
	public static String toStringFormat(Estaciones est, boolean mostrar_descripcion) {
		return "ESTACION" + 
//				"\nCódigo:\t" + est.getCodEst() + 
				"\nMunicipio:\t" + est.getMunicipios().getCodMun()+
				"\nNombre:\t" + est.getNombre() + 
				"\nLatitud:\t" + est.getLatitud() + 
				"\nLongitud:\t" + est.getLongitud() + 
				"\nICA Estacion:\t" + est.getIcaEstacion() +
				"\n--------------------------------------------------------------------";
	}
	
	public static String toString(MunEspNa munespna) {
		return "MunEspNa [" + 
				"codRelacion=" + munespna.getCodRelacion() + 
				", espaciosNaturales=" + munespna.getEspaciosNaturales().getCodEspNatural() + 
				", municipios=" + munespna.getMunicipios().getCodMun() + 
				"]";
	}
	
	public static String toString(Municipios mun, boolean mostrar_descripcion) {
		return "Municipios [" + 
				"codMun=" + mun.getCodMun() + 
				", provincias=" + mun.getProvincias().getCodProv() + 
				", nombre=" + mun.getNombre() + 
				(mostrar_descripcion ? ", descripcion=" + mun.getDescripcion() : "") +
				"]";
	}
	
	public static String toStringFormat(Municipios mun, boolean mostrar_descripcion) {
		return "MUNICIPIO" + 
//				"\nCódigo:\t" + mun.getCodMun() + 
//				"\nProvincia:\t" + mun.getProvincias().getCodProv() + 
				"\nProvincia:\t" + (mun.getProvincias().getCodProv() == 48 ? "Bizkaia" : (mun.getProvincias().getCodProv() == 20 ? "Gipuzkoa" : "Araba")) + 
				"\nNombre:\t" + mun.getNombre() + 
				(mostrar_descripcion ? "\nDescripcion:\t" + mun.getDescripcion() : "") +
				"\n--------------------------------------------------------------------";
	}
	
	public static String toStringFormat(Municipios mun, boolean mostrar_descripcion, boolean estaciones) {
		return "MUNICIPIO" + 
//				"ºnCódigo:  " + mun.getCodMun() + 
//				"\nProvincia:\t" + mun.getProvincias().getCodProv() + 
				"\nProvincia:  " + (mun.getProvincias().getCodProv() == 48 ? "Bizkaia" : (mun.getProvincias().getCodProv() == 20 ? "Gipuzkoa" : "Araba")) + 
				"\nNombre:  " + mun.getNombre() + 
				"\nDescripcion:\n" + mun.getDescripcion();
	}

	public static String toString(Provincias prov) {
		return "Provincias [" + 
				"codProv=" + prov.getCodProv() + 
				", nombre=" + prov.getNombre() + 
				"]";
	}
	
}
