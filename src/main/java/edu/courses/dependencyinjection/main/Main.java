package edu.courses.dependencyinjection.main;

import edu.courses.dependencyinjection.config.AppConfig;
import edu.courses.dependencyinjection.config.BeanDefinition;
import edu.courses.dependencyinjection.test.ChineRobot;
import edu.courses.dependencyinjection.test.SuperBelarusRobot9000;
import edu.courses.dependencyinjection.test.SuperRobotBelarus5000;

public class Main {

	public static void main(String[] args) {
		AppConfig config = new BeanDefinition(new String[]{"config.xml"});
		// singleton
		SuperRobotBelarus5000 robot50001 = (SuperRobotBelarus5000) config.getBean("superBelarus5000");
		SuperRobotBelarus5000 robot50002 = (SuperRobotBelarus5000) config.getBean("superBelarus5000");
		//proxy
		SuperBelarusRobot9000 robot90001 = (SuperBelarusRobot9000) config.getBean("superBelarus9000");
		SuperBelarusRobot9000 robot90002 = (SuperBelarusRobot9000) config.getBean("superBelarus9000");
		//prototype
		ChineRobot chine1 = (ChineRobot) config.getBean("chine");
		ChineRobot chine2 = (ChineRobot) config.getBean("chine");
		
		System.out.println("done!");
		

	}

}
