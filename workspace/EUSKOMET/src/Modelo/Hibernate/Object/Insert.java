package Modelo.Hibernate.Object;

import java.util.logging.Level;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;



public class Insert {

	public static void main(String[] args) {
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		//Insert Provincia
		Provincias prov1 = new Provincias();
		prov1.setCodProv(01);
		prov1.setNombre("Bizkaia");
		
		try {
			//Hemen guk sortutako departamentua gehitzen du 
			session.save(prov1);
			
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTUA existitzen da");
			System.out.printf("Mezua: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,e.getSQLException().getMessage());

		}
		
		
		
		//Insert Mun
		Municipios mun = new Municipios();
		mun.setCodMun(1);
		mun.setNombre("Gernika-lumo");
		mun.setProvincias(prov1);
		
		try {
			//Hemen guk sortutako departamentua gehitzen du 
			session.save(mun);
			
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTUA existitzen da");
			System.out.printf("Mezua: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,e.getSQLException().getMessage());

		}
		
		//Insert Usuario
		Usuarios us1 = new  Usuarios();
		us1.setCodUser(01);
		us1.setNombre("Aitor");
		us1.setContra("csgo");
		us1.setPregunta("Echas unas partidas?");
		us1.setRespuesta("No hay que hacer ethazi");
		
		try {
			//Hemen guk sortutako departamentua gehitzen du 
			session.save(us1);
			
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTUA existitzen da");
			System.out.printf("Mezua: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,e.getSQLException().getMessage());

		}
		
	
	
		
		//Insert Espacios Naturales
		
		EspaciosNaturales esp_na= new EspaciosNaturales();
		esp_na.setCodEspNatural(01);
		esp_na.setNombre("Belatxikieta");
		esp_na.setTipo("montaña");
		esp_na.setLatitud(43.2204692);
		esp_na.setLongitud(-2.733068);
		try {
			//Hemen guk sortutako departamentua gehitzen du 
			session.save(esp_na);
			
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTUA existitzen da");
			System.out.printf("Mezua: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,e.getSQLException().getMessage());

		}
		
		
	
//		//Insert FavoritosEspNaturales 
		
		FavoritosEsp fav_esp = new FavoritosEsp();
		fav_esp.setCodRelacion("01-01");
		fav_esp.setEspaciosNaturales(esp_na);
		fav_esp.setUsuarios(us1);
		
		try {
			//Hemen guk sortutako departamentua gehitzen du 
			session.save(fav_esp);
			
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			System.out.println("DEPARTAMENTUA existitzen da");
			System.out.printf("Mezua: %s%n",e.getMessage());
			System.out.printf("COD ERROR: %d%n",e.getErrorCode());
			System.out.printf("ERROR SQL: %s%n" ,e.getSQLException().getMessage());

		}
		
		
		
		
		tx.commit();
		
		session.close();
		

	}

}

