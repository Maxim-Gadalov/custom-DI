package edu.courses.dependencyinjection.test;

import edu.courses.dependencyinjection.annotation.Proxy;

@Proxy
public class SuperBelarusRobot9000 extends SuperRobotBelarus5000{
	
	private int power;

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
	
	@Override
	public void play(){
		System.out.println("oil price is rising faster");
	}

}
