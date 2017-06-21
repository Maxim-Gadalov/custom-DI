package edu.courses.dependencyinjection.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSchemaType;

public class BeanType {
	@XmlID
	@XmlAttribute(name = "id", required = true)
	@XmlSchemaType(name = "ID")
	protected String id;
	@XmlElement(name = "classpath", required = true)
	protected String classpath;
	@XmlElement(defaultValue = "singleton")
	protected String scope;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getClasspath() {
		return classpath;
	}
	public void setClasspath(String classpath) {
		this.classpath = classpath;
	}
	public String getScope(){
		return scope;
	}
	public void setScope(String scope){
		this.scope = scope;
	}
	@Override
	public String toString() {
		return "BeanType [id=" + id + ", classpath=" + classpath + ", scope=" + scope + "]";
	}

}
