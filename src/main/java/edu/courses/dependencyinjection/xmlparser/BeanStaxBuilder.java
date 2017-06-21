package edu.courses.dependencyinjection.xmlparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.courses.dependencyinjection.bean.BeanEnum;
import edu.courses.dependencyinjection.bean.BeanType;

public class BeanStaxBuilder {
	private static final Logger LOG = LogManager.getLogger(BeanStaxBuilder.class);
	private HashSet<BeanType> beans = new HashSet<>();
	private XMLInputFactory inputFactory;
	
	public BeanStaxBuilder(){
		inputFactory = XMLInputFactory.newInstance();
	}
	
	public Set<BeanType> getBeans(){
		return beans;
	}
	
	public void buildSetBeans(String fileName) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String name;
		try {
			inputStream = new FileInputStream(new File(fileName));
			reader = inputFactory.createXMLStreamReader(inputStream);
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					name = reader.getLocalName().replace("-", "_");
					if (BeanEnum.valueOf(name.toUpperCase()) == BeanEnum.BEAN) {
						BeanType bt = buildBean(reader);
						beans.add(bt);
						}
					}
				}
			} catch (XMLStreamException ex) {
				LOG.error("StAX parsing error! " + ex.getMessage());
			} catch (FileNotFoundException ex) {
				LOG.error("File " + fileName + " not found! " + ex);		
			} finally {
				try {
					if (inputStream != null) {
						inputStream.close();
						}
					} catch (IOException e) {
						LOG.error("Impossible close file "+fileName+" : "+e);
						}
				}
	}
	
	private BeanType buildBean(XMLStreamReader reader) throws XMLStreamException {
		BeanType bt = new BeanType();
		bt.setId(reader.getAttributeValue(null, BeanEnum.ID.getValue()));
		bt.setClasspath(reader.getAttributeValue(null, BeanEnum.CLASSPATH.getValue()));
		bt.setScope(reader.getAttributeValue(null, BeanEnum.SCOPE.getValue()));
	    return bt;
	}
}
