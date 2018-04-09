package common.util;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XmlParser {
	Document document = null;
	String INIT_FAILED_INFO = "Init xml parser failed.";
	String SETINFO_FAILED_INFO = "Set xml infomation failed.";
	public static Logger log = Logger.getLogger(XmlParser.class);
	
	public XmlParser(String file){
		init(file);
	}
	
	public void init(String file){
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(file);
		} catch (ParserConfigurationException e) {
			log.error(INIT_FAILED_INFO + "ParserConfigurationException.");
		} catch (IOException e) {
			log.error(INIT_FAILED_INFO + "IOException.");
		} catch (SAXException e) {
			log.error(INIT_FAILED_INFO + "SAXException");
		}
		
	}
	
	public NodeList getNodeListByName(String name){
		return document.getElementsByTagName(name);		
	}
	
	public NodeList getFirstNodeList(){						
		return document.getElementsByTagName(document.getFirstChild().getNodeName());				
	}
	
	
}
