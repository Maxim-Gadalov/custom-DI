package edu.courses.dependencyinjection.proxy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyProxyFactory {
	
	public Class<?> getProxy(Class<?> originalType) throws InstantiationException, IllegalAccessException{
		Interceptor interceptor = new Interceptor();
		return new ByteBuddy()
				.subclass(originalType)
				.method(ElementMatchers.any().and(ElementMatchers.not(ElementMatchers.isDeclaredBy(Object.class))))
				.intercept(MethodDelegation.to(interceptor))
				.make()
				.load(originalType.getClassLoader(),ClassLoadingStrategy.Default.WRAPPER)
				.getLoaded();
	}
}
