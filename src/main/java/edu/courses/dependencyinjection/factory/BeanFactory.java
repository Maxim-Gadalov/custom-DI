package edu.courses.dependencyinjection.factory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import edu.courses.dependencyinjection.annotation.Proxy;
import edu.courses.dependencyinjection.annotation.Scope;
import edu.courses.dependencyinjection.bean.BeanType;
import edu.courses.dependencyinjection.classloader.CustomClassLoader;
import edu.courses.dependencyinjection.exception.IllegalClassFileException;
import edu.courses.dependencyinjection.proxy.ByteBuddyProxyFactory;

public class BeanFactory {
	private static final String BEAN_CLASSPATH_ERROR = "Bean classpath is not valid, please verify your bean config file";
	private static final String PROTOTYPE = "prototype";
	
	private static CustomClassLoader loader;
	private static ConcurrentMap<BeanType,Object> container = new ConcurrentHashMap<>();
	private ByteBuddyProxyFactory proxy = new ByteBuddyProxyFactory();
	
	public BeanFactory(){
		loader = new CustomClassLoader();
	}
	
	public BeanFactory(String classpath){
		if(classpath.endsWith(".jar")){
			throw new IllegalClassFileException(BEAN_CLASSPATH_ERROR);
		}
		loader = new CustomClassLoader(classpath);
	}
	
	public Object getBeanObject(BeanType bean) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Class<?> clazz = loader.loadClass(bean.getClasspath());
		if(clazz.isAnnotationPresent(Proxy.class)){
			return proxy.getProxy(clazz).newInstance();
		}
		if(clazz.isAnnotationPresent(Scope.class) & clazz.getAnnotation(Scope.class).value().getValue().equals(PROTOTYPE)){
			return clazz.newInstance();
		}
		if(bean.getScope() != null){
			if(bean.getScope().equals(PROTOTYPE)){
				return clazz.newInstance();
			}
		}
		if(container.containsKey(bean)){
			return container.get(bean);
		}
		Object obj = clazz.newInstance();
		container.put(bean, obj);
		return obj;
		
	}
	
	

}
