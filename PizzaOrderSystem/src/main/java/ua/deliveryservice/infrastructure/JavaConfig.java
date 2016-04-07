package ua.deliveryservice.infrastructure;

import java.util.Map;
import java.util.HashMap;

import ua.deliveryservice.repository.InMemOrderRepository;
import ua.deliveryservice.repository.InMemPizzaRepository;
import ua.deliveryservice.service.SimpleOrderService;
import ua.deliveryservice.service.SimplePizzaService;

public class JavaConfig implements Config {

	private Map<String, Class<?>> ifc2Class = new HashMap<>();
	
	public JavaConfig() {
		ifc2Class.put("orderRepository", InMemOrderRepository.class);
		ifc2Class.put("pizzaRepository", InMemPizzaRepository.class);
		ifc2Class.put("pizzaService", SimplePizzaService.class);
		ifc2Class.put("orderService", SimpleOrderService.class);
	}
	
	@Override
	public <T> Class<T> getImpl(String ifc) {
		
		return (Class<T>)ifc2Class.get(ifc);
	}
}
