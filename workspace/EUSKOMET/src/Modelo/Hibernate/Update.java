package Modelo.Hibernate;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;

public class Update {
	
	public static void actualizar(Estaciones o) {
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(o);
			System.out.println("Ondo aldatu ahal izan da");
		} catch (ConstraintViolationException e) {
			session.close();
		}
		tx.commit();
		session.close();
		
	}
	
	public static void actualizar(Municipios o) {
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(o);
			System.out.println("Ondo aldatu ahal izan da");
		} catch (ConstraintViolationException e) {
			session.close();
		}
		tx.commit();
		session.close();
		
	}
	
	public static void actualizar(EspaciosNaturales o) {
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(o);
			System.out.println("Ondo aldatu ahal izan da");
		} catch (ConstraintViolationException e) {
			session.close();
		}
		tx.commit();
		session.close();
		
	}
	
	public static void actualizar(MunEspNa o) {
		
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(o);
			System.out.println("Ondo aldatu ahal izan da");
		} catch (ConstraintViolationException e) {
			session.close();
		}
		tx.commit();
		session.close();
		
	}

}
