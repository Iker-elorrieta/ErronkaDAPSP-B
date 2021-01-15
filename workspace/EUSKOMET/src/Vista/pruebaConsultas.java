package Vista;

import java.util.ArrayList;

import Modelo.BBDD.Consultas;
import Modelo.Objetuak.Municipio;

public class pruebaConsultas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Realizar conexión con la base de datos");
		
		ArrayList<Municipio> arrayMunicipios = new ArrayList<Municipio>();
		
		String sql = "SELECT * FROM municipios";
		
		arrayMunicipios = Consultas.consultaMunicipios(sql);
		
		for (Municipio mun : arrayMunicipios) {			
			System.out.println(mun.toString());
		}
		
	}

}
