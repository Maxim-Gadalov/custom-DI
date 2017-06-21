package edu.courses.dependencyinjection.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
	ScopeType value() default ScopeType.SINGLETON;
	
	public enum ScopeType{
		SINGLETON("singleton"),
		PROTOTYPE("prototype");
		private String value;
		ScopeType(String v){
			this.value = v;
		}
		public String getValue(){
			return value;
		}
		}

}
