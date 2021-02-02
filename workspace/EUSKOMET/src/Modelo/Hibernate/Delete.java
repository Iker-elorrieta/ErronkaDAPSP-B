package Modelo.Hibernate;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import Modelo.Hibernate.Object.Estaciones;

public class Delete {
	
	public static int borrarHistoricosViejos(Estaciones est) {
		
		int filas = 0;
		try {
			java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);
		SessionFactory sesioa = HibernateUtil.getSessionFactory();
		Session session = sesioa.openSession();
		
		Date semana = Date.from(LocalDate.now().minusDays(7).atStartOfDay(ZoneId.systemDefault()).toInstant());

		String s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(semana);
		System.out.println(s);
		int i = est.getCodEst();
		System.out.println(i);
		Query q1 = session.createQuery("DELETE FROM Historico WHERE estaciones.codEst = " + i + " AND fecha < '" + s + "'");
		
		filas = q1.executeUpdate();
		
		session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return filas;
		
	}

}
