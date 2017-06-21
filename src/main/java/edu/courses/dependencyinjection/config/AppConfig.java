package edu.courses.dependencyinjection.config;

import java.io.File;

public interface AppConfig {
	String PATH = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
	Object getBean(String beanId);

}
