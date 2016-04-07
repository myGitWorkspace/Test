package ua.deliveryservice.infrastructure.proxy;

import java.lang.reflect.Proxy;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationHandler;

import ua.deliveryservice.annotations.Benchmark;

public class ProxyForBenchmarkAnnotation {

	private Class<?> type;
	
	public Object findBenchmarkAnnotation(Object o) {
		
		this.type = o.getClass();
		
		for(Method method : this.type.getMethods()) {
			
			if(method.isAnnotationPresent(Benchmark.class)) {
				
//				try {
//					System.out.println("aaa="+method.getName()+":" + method.invoke(o, 1) );
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
					
				//System.out.println("aaa="+method.getName()+":" +method.isAnnotationPresent(Benchmark.class));
				
				return getProxy(o);
			}
			
		}
		return o;
	}
	
	public Object getProxy(final Object o) {		
		
		return Proxy.newProxyInstance(
				this.type.getInterfaces()[0].getClassLoader(),
				new Class[] { this.type.getInterfaces()[0] },
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						Object resultMethod = null;
						
						Method m = o.getClass().getMethod(method.getName(), method.getParameterTypes());
						
						if(m.isAnnotationPresent(Benchmark.class)) {
							
							long start = System.nanoTime();
							resultMethod = method.invoke(o, args);
							long time = System.nanoTime() - start;
							System.out.println("Method " + method.getName() + " from interfc=" + ProxyForBenchmarkAnnotation.this.type.getInterfaces()[0] + " worked " + time + " nanoseconds");
						}
						else {
							resultMethod = method.invoke(o, args);
							
						}
						return resultMethod;
					}
				});
	}
}
