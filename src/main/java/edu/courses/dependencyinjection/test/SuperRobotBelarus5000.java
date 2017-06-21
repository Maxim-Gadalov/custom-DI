package edu.courses.dependencyinjection.test;

import edu.courses.dependencyinjection.annotation.Scope;
import edu.courses.dependencyinjection.annotation.Scope.ScopeType;

@Scope(ScopeType.SINGLETON)
public class SuperRobotBelarus5000 implements Robot{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void play() {
		System.out.println("oil price is rising");
		
	}
	

}
