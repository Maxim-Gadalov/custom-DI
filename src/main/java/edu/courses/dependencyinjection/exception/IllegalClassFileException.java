package edu.courses.dependencyinjection.exception;

public class IllegalClassFileException extends RuntimeException{
	
	private static final long serialVersionUID = 4562092621872531264L;
	
	public IllegalClassFileException(){
		super();
	}
	
	public IllegalClassFileException(String message){
		super(message);
	}
	
	public IllegalClassFileException(Throwable e){
		super(e);
	}

}
