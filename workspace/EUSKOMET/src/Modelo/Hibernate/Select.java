package Modelo.Hibernate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.Object.Provincias;

public class Select {
	
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Estaciones> obtEstaciones() {

		LinkedHashMap<Integer, Estaciones> lhm = new LinkedHashMap<Integer, Estaciones>();
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		
		Query q = session.createQuery("from Estaciones");
		
		for (Estaciones e : (List<Estaciones>) q.list()) {
			lhm.put(e.getCodEst(), e);
		}
		
		session.close();
		
		return lhm;
		
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, EspaciosNaturales> obtEspNaturales() {

		LinkedHashMap<Integer, EspaciosNaturales> lhm = new LinkedHashMap<Integer, EspaciosNaturales>();
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		
		Query q = session.createQuery("from EspaciosNaturales");
		
		for (EspaciosNaturales e : (List<EspaciosNaturales>) q.list()) {
			lhm.put(e.getCodEspNatural(), e);
		}
		
		session.close();
		
		return lhm;
		
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Municipios> obtMunicipios() {

		LinkedHashMap<Integer, Municipios> lhm = new LinkedHashMap<Integer, Municipios>();
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		
		Query q = session.createQuery("from Municipios");
		
		for (Municipios e : (List<Municipios>) q.list()) {
			lhm.put(e.getCodMun(), e);
		}
		
		session.close();
		
		return lhm;
		
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<String, MunEspNa> obtMunEspNats() {

		LinkedHashMap<String, MunEspNa> lhm = new LinkedHashMap<String, MunEspNa>();
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		//Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from MunEspNa");
		
		for (MunEspNa e : (List<MunEspNa>) q.list()) {
			lhm.put(e.getCodRelacion(), e);
		}
		
		session.close();
		
		return lhm;
		
	}
	
	@SuppressWarnings("unchecked")
	public static LinkedHashMap<Integer, Provincias> obtProvincias() {

		LinkedHashMap<Integer, Provincias> lhm = new LinkedHashMap<Integer, Provincias>();
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		//Transaction tx = session.beginTransaction();
		
		Query q = session.createQuery("from Provincias");
		
		for (Provincias e : (List<Provincias>) q.list()) {
			lhm.put(e.getCodProv(), e);
		}
		
		session.close();
		
		return lhm;
		
	}
	
	public static int obtCodEspNat() {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		
			Query q2 = session.createQuery("SELECT MAX(codEspNatural) FROM EspaciosNaturales");
		
			codigo = q2.list().get(0) != null ? (int) q2.list().get(0) + 1 : 1;
		
		session.close();
		
		return codigo;
		
	}
	
	public static int obtCodEstacion() {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

			Query q2 = session.createQuery("SELECT MAX(codEst) FROM Estaciones");
		
			codigo = q2.list().get(0) != null ? (int) q2.list().get(0) + 1 : 1;
		
		session.close();
		
		return codigo;
		
	}
	
	public static int obtCodMunicipio() {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

			Query q2 = session.createQuery("SELECT MAX(codMun) FROM Municipios");
		
			System.out.println(q2.list());
			
			codigo = q2.list().get(0) != null ? (int) q2.list().get(0) + 1 : 1;
		
		session.close();
		
		return codigo;
		
	}
	
	public static int existeEspNat(String nom) {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

		Query q1 = session.createQuery("SELECT codEspNatural FROM EspaciosNaturales WHERE nombre = '" + nom + "'");
		
		codigo = (q1.list().size() > 0 ? (int) q1.list().get(0) : -1);
		
		session.close();
		
		return codigo;
		
	}
	
	public static int existeEstacion(String nom) {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

		Query q1 = session.createQuery("SELECT codEst FROM Estaciones WHERE nombre = '" + nom + "'");
		
		codigo = (q1.list().size() > 0 ? (int) q1.list().get(0) : -1);
		
		session.close();
		
		return codigo;
		
	}
	
	public static int existeMunicipio(String nom) {
		
		int codigo = 0;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

		Query q1 = session.createQuery("SELECT codMun FROM Municipios WHERE nombre = '" + nom + "'");
		
		codigo = (q1.list().size() > 0 ? (int) q1.list().get(0) : -1);
		
		session.close();
		
		return codigo;
		
	}
	
	public static boolean existeMunEspNa(String nom) {
		
		boolean existe;
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		LogManager.getLogManager().getLogger("").setLevel(Level.OFF);
		Session session = sesioa.openSession();

		Query q1 = session.createQuery("SELECT codRelacion FROM MunEspNa WHERE codRelacion = '" + nom + "'");
		
		existe = q1.list().size() > 0;
		
		session.close();
		
		return existe;
		
	}

}
