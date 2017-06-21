package edu.courses.dependencyinjection.config;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import edu.courses.dependencyinjection.bean.BeanType;
import edu.courses.dependencyinjection.exception.IllegalBeanIdDefinition;
import edu.courses.dependencyinjection.factory.BeanFactory;
import edu.courses.dependencyinjection.xmlparser.BeanStaxBuilder;

public class BeanDefinition implements AppConfig{
	
	private ConcurrentMap<String,BeanType> map = new ConcurrentHashMap<>();
	private BeanFactory factory = new BeanFactory();
	
	public BeanDefinition(String[] ConfigFiles){
		createBeanTypes(ConfigFiles);
	}

	@Override
	public Object getBean(String beanId) {
		BeanType bean = map.get(beanId);
		if(bean == null){
			throw new IllegalBeanIdDefinition("Bean with id = " + beanId + "not found!");
		}
		try {
			return factory.getBeanObject(bean);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// log error
			e.printStackTrace();
			return null;
		}
	}
	
	private void createBeanTypes(String[] configFiles){
		BeanStaxBuilder builder = new BeanStaxBuilder();
		for(int i = 0;i < configFiles.length;i++){
			builder.buildSetBeans(PATH + configFiles[i]);
		}
		Iterator<BeanType> iterator = builder.getBeans().iterator();
		while(iterator.hasNext()){
			BeanType bean = iterator.next();
			map.put(bean.getId(), bean);
		}		
	}
	
	public ConcurrentMap<String,BeanType> getMap(){
		return map;
	}
	
}
