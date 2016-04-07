package ua.deliveryservice.infrastructure;

public interface ApplicationContext {
	public Object getBean(String beanName) throws Throwable;
}