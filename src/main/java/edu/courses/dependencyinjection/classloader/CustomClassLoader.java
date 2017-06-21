package edu.courses.dependencyinjection.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/** ссылки на ресурсы :
 * http://samolisov.blogspot.com.by/2008/01/java.html
 * https://docs.oracle.com/javase/8/docs/api/
 * http://www.journaldev.com/349/java-classloader
 * https://dzone.com/articles/java-classloader-handling
 * @author HadalauM
 *
 */
public class CustomClassLoader extends ClassLoader{
	private static final String CLASSPATH_FILE_WARNING = "Current file not found, please verefy your classpath!";

	private String fileName;
	private boolean fileTypeFlag;
	private Map<String,Class<?>> cache = new HashMap<>();
	
	
	public CustomClassLoader(String jarOrClassFileName){
		if(isJarFile(jarOrClassFileName)){
			this.fileTypeFlag = true;
		} else{
			this.fileTypeFlag = false;
		}
		this.fileName = jarOrClassFileName;
		cacheClasses();
	}
	
	public CustomClassLoader() {

	}
	
	@Override
	 public synchronized Class<?> loadClass(String name) throws ClassNotFoundException {
	        Class<?> result = cache.get(normalize(name));	      
	        if (result == null){
	        	result = getClass().getClassLoader().loadClass(name);
	        	if(result == null){
	        		result = super.findSystemClass(name); 
	        	} else{
	        		cache.put(result.getName(), result);
	         	}
	        }       	    
	        return result;

	    }
	 @Override 
	 protected synchronized Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException{
		 Class<?> result = findClass(name);
	        if (resolve)
	            resolveClass(result);
	        return result;
	 }
	 
	 private void cacheClasses() {
		 try{
			 if(fileTypeFlag){
				 JarFile jarFile = new JarFile(fileName);
	             Enumeration<JarEntry> entries = jarFile.entries();
	             while (entries.hasMoreElements()) {
	            	 JarEntry jarEntry = (JarEntry) entries.nextElement();
	            	 if (isClassFile(normalize(jarEntry.getName()))) {                   
	            		 byte[] classData = loadClassData(jarFile, jarEntry); 
	            		 System.out.println("Unpacking jar file... prepearing for loading in cache.");
	            		 if (classData != null) {
	            			 Class<?> clazz = defineClass(stripClassName(normalize(jarEntry.getName())), classData, 0, classData.length);
	                         cache.put(clazz.getName(), clazz);
	                         System.out.println("*** class " + clazz.getName() + " loaded in cache");
	                         }
	            		 }
	            	 }
	             } else{
	            			 Class<?> clazz = getClass().getClassLoader().loadClass(fileName);
	            			 cache.put(clazz.getName(), clazz);
	            			 System.out.println("*** class " + clazz.getName() + " loaded in cache");
	            }
			 }catch (ClassNotFoundException | IOException ex) {
				 System.err.println(CLASSPATH_FILE_WARNING);
			 }
     }
	 
	 private String normalize(String className) {
		 return className.replace(File.separatorChar, '.');
	 }
	 
	 private boolean isClassFile(String className){
		 return className.endsWith(".class");
	 }
	 
	 private String stripClassName(String className) {
		 return className.substring(0, className.length() - 6);
	 }
	 
	 private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException {
	        long size = jarEntry.getSize();     
	        if (size == -1 || size == 0)
	            return null;
	        byte[] data = new byte[(int)size];
	        InputStream in = jarFile.getInputStream(jarEntry);
	        in.read(data);
	        return data;
	 }
	 
	 private boolean isJarFile(String file){
		 return file.toLowerCase().endsWith(".jar");
	 }
	 
	 public Map<?,?> getAllCache(){
		 return cache;
	 }
	 
	 @Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> result = super.findClass(name);
		if(result == null){
			result = loadClass(name);
		}
		return result;
	}
	 
	 @Override
	public URL getResource(String name) {
		URL url = super.getResource(name);
		if(url == null){
			url = findResource(name);
		}
		return url;
	}
	 
	 @Override
	public Enumeration<URL> getResources(String name) throws IOException {
		 Enumeration<URL> urls = super.getResources(name);
		 if(urls == null){
			 urls = findResources(name);
		 }
		return urls;
	}
	 
}
