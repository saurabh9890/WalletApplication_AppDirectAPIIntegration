package com.challenge.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class EventXML {
	private String event_type = "";
	private String email = "";
	private String subscrition = "";

	public EventXML(String xml) {
		parse(xml);
	}
	
	private void parse(String xml) {
		DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
		DocumentBuilder b = null;
		StringReader reader = null;
		try {
			reader = new StringReader(xml);
			InputSource inputSource = new InputSource(reader);
			b = f.newDocumentBuilder();
			Document doc = b.parse(inputSource);
			
			NodeList nList = doc.getElementsByTagName("email");
			if (nList.getLength() >= 1) email = nList.item(0).getTextContent();

			nList = doc.getElementsByTagName("type");
			if (nList.getLength() >= 1) event_type = nList.item(0).getTextContent();
		} catch (ParserConfigurationException ignore) {
			//ignore.printStackTrace();
		} catch (SAXException ignore) {
			//ignore.printStackTrace();
		} catch (IOException ignore) {
			//ignore.printStackTrace();
		}  finally {
			if (reader != null){
				reader.close();
			}
		}
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getEventType() {
		return event_type;
	}

	public String getSubscription() {
		return subscrition;
	}
}