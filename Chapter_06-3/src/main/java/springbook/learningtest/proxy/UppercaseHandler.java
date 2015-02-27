package springbook.learningtest.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wglee21g@gmail.com
 */
public class UppercaseHandler implements InvocationHandler {
	private Hello target;
	
	public UppercaseHandler(Hello target) {
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object returnValue = method.invoke(target, args);
		
		if (returnValue instanceof String && method.getName().startsWith("say")) {
			return ((String) returnValue).toUpperCase();
		}
		return returnValue;
	}
}
