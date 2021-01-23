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



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException; 

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane; 

public class Insert {
	
	public static void insertar(Estaciones o) {
		
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(o);
			tx.commit();
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.close();
		}
		if (session.isOpen()) session.close();
		
	}
	
	public static void insertar(Municipios o) {
		
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			
			session.save(o);
			tx.commit();
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.close();
		}
		if (session.isOpen()) session.close();
		
	}
	
	public static void insertar(EspaciosNaturales o) {
		
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(o);
			tx.commit();
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.close();
		}
		if (session.isOpen()) session.close();
		
	}
	
	public static void insertar(MunEspNa o) {
		
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(o);
			tx.commit();
			System.out.println("Ondo sortu ahal izan da");
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			session.close();
		}
		if (session.isOpen()) session.close();
		
	}
	


	 
	    
	    
	 
	    
	  
	
	      

	

}

