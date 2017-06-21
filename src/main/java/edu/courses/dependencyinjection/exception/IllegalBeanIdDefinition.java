package edu.courses.dependencyinjection.exception;

public class IllegalBeanIdDefinition extends RuntimeException{
	
	private static final long serialVersionUID = 6694031653492971449L;

	public IllegalBeanIdDefinition(){
		super();
	}
	
	public IllegalBeanIdDefinition(String message){
		super(message);
	}
	
	public IllegalBeanIdDefinition(Throwable e){
		super(e);
	}

}
