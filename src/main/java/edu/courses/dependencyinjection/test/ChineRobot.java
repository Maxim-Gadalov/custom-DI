package edu.courses.dependencyinjection.test;

import edu.courses.dependencyinjection.annotation.Scope;
import edu.courses.dependencyinjection.annotation.Scope.ScopeType;

@Scope(ScopeType.PROTOTYPE)
public class ChineRobot {

	public void play(){
		System.out.println("Made in Chine");
	}
}
