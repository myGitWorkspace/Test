package ua.deliveryservice.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Proxy;
import java.lang.reflect.InvocationHandler;

import ua.deliveryservice.annotations.PostCreate;
import ua.deliveryservice.annotations.Benchmark;
import ua.deliveryservice.infrastructure.proxy.ProxyForBenchmarkAnnotation;


public class JavaConfigApplicationContext implements ApplicationContext {

	private final Config config;
	
	private final Map<String, Object> beans = new HashMap<>();
	
	public JavaConfigApplicationContext(Config config) {
		this.config = config;
	}
	
	@Override
	public Object getBean(String beanName) throws Throwable {

		Class<?> type = config.getImpl(beanName);
		Object bean = beans.get(beanName);		
		
		if(bean != null)
			return bean;
		
		BeanBuilder builder = new BeanBuilder(type);
		builder.createBean();
		builder.createBeanProxy();
        builder.callPostCreateMethod();
        builder.callInitMethod(); 
		
				
		bean = builder.build();
		
		beans.put(beanName, bean);
		
		return bean;
	}
	
	class BeanBuilder {
		
		private Class<?> type;
		Object bean;
		boolean isInitMethodCalled = false;
		
		public BeanBuilder(Class<?> type) {
			//System.out.println("type from config=" + type);
			this.type = type;			
		}
		
		public void createBean() throws Throwable {
			
			Constructor<?> constructor = type.getConstructors()[0];
			Parameter[] parameters = constructor.getParameters();
			if(parameters.length == 0) {
				bean = this.type.newInstance();
			} else {
				Object[] params = new Object[parameters.length];
				for(int i=0; i<parameters.length; i++) {
					String upperCaseBeanName = parameters[i].getType().getSimpleName();					
					String beanName = String.valueOf(upperCaseBeanName.charAt(0)).toLowerCase() + upperCaseBeanName.substring(1, upperCaseBeanName.length());
					//System.out.println("name to inject =" + beanName);
					params[i] = JavaConfigApplicationContext.this.getBean(beanName);
				}
				bean = constructor.newInstance(params);
			}
			
			/*
			if(bean instanceof ua.deliveryservice.repository.InMemPizzaRepository)
				System.out.println("bbb=" + ((ua.deliveryservice.repository.InMemPizzaRepository)bean).f(2));
			*/
			
		}
		
		public void callInitMethod() throws Throwable {
			//System.out.println("Proxy class name is " + bean.getClass().getInterfaces()[0]);
			//System.out.println("aaa"+this.type.cast(bean));
			
			/*
			if(bean instanceof ua.deliveryservice.repository.InMemPizzaRepository) {
				
				for(Method method1 : ((this.type.cast(bean))).getClass().getMethods()) {
					System.out.println("asd="+method1.getName());
					
					if(method.isAnnotationPresent(Benchmark.class)) {
						try {
							System.out.println("aaa="+method.getName()+":" + method.invoke(bean, 1) + ":" + ((ua.deliveryservice.repository.InMemPizzaRepository)bean).find(1));
							}catch(Exception e) {
								e.printStackTrace();
							}					
					}
					
					
				}
			}
*/
			
			if(this.isInitMethodCalled)
				return;
						
			String methodName = "init";
			
			runBeanMethod(methodName);
			//System.out.println("Init called!!"+isInitMethodCalled);
			//System.out.println("type afterConstruct=" + clazz);			
			
		}
		
        private void callPostCreateMethod() throws Throwable {
        	Class<?> clazz = this.type;
        	String initMethodName = "init";
			for(Method method : clazz.getDeclaredMethods()) {
				if(method.isAnnotationPresent(PostCreate.class) ) {					
					runBeanMethod(method.getName());					
					if(method.getName().equals(initMethodName))
						this.isInitMethodCalled = true;
				}
			}
        }
        
        private void runBeanMethod(String methodName) throws Throwable {
        	
			Class<?> clazz = bean.getClass();
        	
			if (Proxy.isProxyClass(clazz) ) {
				
				for(Method method : this.type.getMethods()) {
					if(method.getName().equals(methodName)) {
						
						Proxy proxy = (Proxy)bean;
						InvocationHandler handler = proxy.getInvocationHandler(proxy);
						/*
						for(Method m : this.type.getMethods()) {
							System.out.println("name="+":"+methodName+":"+m.getName()+":"+this.type.getMethod(m.getName(),m.getParameterTypes()));	
						}
						Method md = this.type.getMethod(methodName);
						
						Parameter[] params = this.type.get.getMethod(methodName).get.getParameterTypes();
						*/
						
						Object[] params = new Object[method.getParameters().length];
						for(int i=0; i<method.getParameters().length; i++) {
							params[i] = method.getParameters()[i];							
						}							
						handler.invoke(proxy, method, params);						
					}
					
				}
				
			} else {
				Method method = null;
				try {
					method = clazz.getDeclaredMethod(methodName);
				} catch(NoSuchMethodException e) {
					return;
				}
							
				if(method != null) {
					method.invoke(bean);									
				}
			}
        }
		
		public void createBeanProxy() {
			
			ProxyForBenchmarkAnnotation p = new ProxyForBenchmarkAnnotation();
			bean = p.findBenchmarkAnnotation(bean);
			
		}
		
		public Object build() {
			return bean;
		}
		
	}
}
