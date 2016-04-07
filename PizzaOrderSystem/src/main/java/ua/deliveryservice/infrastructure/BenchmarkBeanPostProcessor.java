package ua.deliveryservice.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import ua.deliveryservice.annotations.Benchmark;


public class BenchmarkBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(final Object o, String string)
			throws BeansException {
		Class<?> type = o.getClass();		
		
		for(Method method : type.getMethods()) {
			
			if(method.isAnnotationPresent(Benchmark.class)) {
				//System.out.println("TYPE="+type);
				Object proxy = Proxy.newProxyInstance(
						type.getClassLoader(),
						(o.getClass().getName().toLowerCase().contains("cglib")) ? o.getClass().getSuperclass().getInterfaces() : type.getInterfaces(),
						new InvocationHandler() {
							@Override
							public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
								
								Object resultMethod = null;
								
								Method m = o.getClass().getMethod(method.getName(), method.getParameterTypes());
								
								if(m.isAnnotationPresent(Benchmark.class)) {
									
									long start = System.nanoTime();
									resultMethod = method.invoke(o, args);
									long time = System.nanoTime() - start;
									System.out.println("Method " + method.getName() + " from interface=" + o.getClass().getInterfaces()[0] + " worked " + time + " nanoseconds");
								}
								else {
									resultMethod = method.invoke(o, args);
									
								}
								return resultMethod;
							}
						});
				
				/*
				for(Method m : o.getClass().getMethods()) {
					System.out.println("class="+m.getName());
				}
				System.out.println( o.getClass().getSuperclass().getInterfaces()[0] );
				if (o.getClass().getName().toLowerCase().contains("cglib")) {
					System.out.println("true");
				}
				*/
				
				return proxy;
			}
		}
		
		return o;	

	}

	@Override
	public Object postProcessAfterInitialization(Object o, String string) throws BeansException {
		
		return o;
	}

}
