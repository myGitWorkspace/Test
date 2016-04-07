package ua.deliveryservice.infrastructure;

public interface Config {
	
	<T> Class<T> getImpl(String ifc);
}
