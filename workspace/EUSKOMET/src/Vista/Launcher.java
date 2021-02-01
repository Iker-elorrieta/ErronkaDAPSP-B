package Vista;

import java.net.http.HttpClient;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import Modelo.DatuKudeaketa.JSONIrakurri;
import Modelo.DatuKudeaketa.XMLIdatzi;
import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.ObjectExtras.ToString;

public class Launcher {

	public static void main(String[] args) {
		
		ejecutar();

	}

	@SuppressWarnings("unchecked")
	public static int ejecutar() {
		
		int estado = 1;
		
		trustEveryone();
		
		XMLIdatzi xmlidatzi = new XMLIdatzi();
		
		JSONIrakurri jsonirak = new JSONIrakurri();
		
		LinkedHashMap<Integer, Municipios> lhmMunicipio = jsonirak.herriaIrakurri();
		
		for (Municipios e : lhmMunicipio.values()) {
			System.out.println(ToString.toString(e, true));
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
			System.out.println(ToString.toString(e, true));
		}
		
		xmlidatzi.XMLEspazioNaturalak(lhmEspNat);
		
		for (MunEspNa e : alMunEsp) {
			System.out.println(ToString.toString(e));
		}
		
		xmlidatzi.XMLMunEspNa(alMunEsp);
		
		estado = 0;
		return estado;
	}
	
	private static void trustEveryone() {
		try {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
				public boolean verify(String arg0, SSLSession arg1) {
					return false;
				}
			});
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null, new X509TrustManager[] {new X509TrustManager() {
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return new X509Certificate[0];
				}
			}}, new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}