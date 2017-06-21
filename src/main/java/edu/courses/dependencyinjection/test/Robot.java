package edu.courses.dependencyinjection.test;

public interface Robot {
	void play();
	default void start(){
		System.out.println("Trtrtrt");
	}

}
