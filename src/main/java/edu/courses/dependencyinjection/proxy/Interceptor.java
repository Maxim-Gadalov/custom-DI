package edu.courses.dependencyinjection.proxy;

import java.util.concurrent.Callable;

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

public class Interceptor {
	
	@RuntimeType
    @BindingPriority(Integer.MAX_VALUE)
    public Object _default(@SuperCall Callable<?> zuper, @AllArguments Object[] args) throws Exception {
        return zuper.call();
    }

}
