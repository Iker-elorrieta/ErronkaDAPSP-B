package Modelo.Hibernate.ObjectExtras;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;

public class ToString {

	public static String toString(EspaciosNaturales espnat) {
		return "EspaciosNaturales [" + 
				"codEspNatural=" + espnat.getCodEspNatural() + 
				", nombre=" + espnat.getNombre() + 
				", tipo=" + espnat.getTipo() + 
				", latitud=" + espnat.getLatitud() + 
				", longitud=" + espnat.getLongitud() + 
				"]";
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
	
	public static String toString(MunEspNa munespna) {
		return "MunEspNa [" + 
				"codRelacion=" + munespna.getCodRelacion() + 
				", espaciosNaturales=" + munespna.getEspaciosNaturales().getCodEspNatural() + 
				", municipios=" + munespna.getMunicipios().getCodMun() + 
				"]";
	}
	
	public static String toString(Municipios mun) {
		return "Municipios [" + 
				"codMun=" + mun.getCodMun() + 
				", provincias=" + mun.getProvincias().getCodProv() + 
				", nombre=" + mun.getNombre() + 
				"]";
	}
	
}
