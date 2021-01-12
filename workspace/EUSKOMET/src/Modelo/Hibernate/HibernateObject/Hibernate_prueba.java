package Modelo.Hibernate.HibernateObject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.logging.Level;

public class Hibernate_prueba {

	public static void main(String[] args) {
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		Session session = sesioa.openSession();
		Transaction tx = session.beginTransaction();

	}

}
