package Modelo.DatuKudeaketa;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Modelo.Hibernate.Object.EspaciosNaturales;
import Modelo.Hibernate.Object.Estaciones;
import Modelo.Hibernate.Object.MunEspNa;
import Modelo.Hibernate.Object.Municipios;
import Modelo.Hibernate.ObjectExtras.GetElement;

public class XMLIdatzi {

	public void XMLEstazioak(LinkedHashMap<Integer, Estaciones> lhm) {
		String name = "Estazioak.xml";
		String folder = "CopiasXML/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return;
		File file = new File(folder + name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();
		Element emailelement = doc.createElement("estazioak");
		for (Estaciones o : lhm.values()) {
			emailelement.appendChild(GetElement.getElement(doc, o));
		}
		doc.appendChild(emailelement);
		formatXml(doc, "CopiasXML/Estazioak.xml");
	}
	
	public void XMLEspazioNaturalak(LinkedHashMap<Integer, EspaciosNaturales> lhm) {
		String name = "EspaciosNaturales.xml";
		String folder = "CopiasXML/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return;
		File file = new File(folder + name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();
		Element emailelement = doc.createElement("espacios_naturales");
		for (EspaciosNaturales o : lhm.values()) {
			emailelement.appendChild(GetElement.getElement(doc, o));
		}
		doc.appendChild(emailelement);
		formatXml(doc, "CopiasXML/EspaciosNaturales.xml");
	}

	public void XMLMunicipio(LinkedHashMap<Integer, Municipios> lhm) {
		String name = "Municipios.xml";
		String folder = "CopiasXML/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return;
		File file = new File(folder + name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();
		Element emailelement = doc.createElement("municipios");
		for (Municipios o : lhm.values()) {
			emailelement.appendChild(GetElement.getElement(doc, o));
		}
		doc.appendChild(emailelement);
		formatXml(doc, "CopiasXML/Municipios.xml");
	}

	public void XMLMunEspNa(ArrayList<MunEspNa> al) {
		String name = "MunEspNa.xml";
		String folder = "CopiasXML/";
		File dir = new File(folder);
		if (!dir.exists())
			if (!dir.mkdir())
				return;
		File file = new File(folder + name);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		Document doc = docBuilder.newDocument();
		Element emailelement = doc.createElement("erlazioak");
		for (MunEspNa o : al) {
			emailelement.appendChild(GetElement.getElement(doc, o));
		}
		doc.appendChild(emailelement);
		formatXml(doc, "CopiasXML/MunEspNa.xml");
	}

	public static void formatXml(Document doc, String url) {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = null;
		try {
			transformer = transformerFactory.newTransformer();
		} catch (TransformerConfigurationException e1) {
			e1.printStackTrace();
		}
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File(url));
		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
