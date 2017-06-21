package edu.courses.dependencyinjection.bean;

public enum BeanEnum {
	BEANS("beans"),
	BEAN("bean"),
	ID("id"),
	CLASSPATH("classpath"),
	SCOPE("scope");
	private String value;
	private BeanEnum(String v){
		this.value = v;
	}
	public String getValue(){
		return value;
	}

}
