package ua.deliveryservice.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class OrderDTO {
	
	private Map<Integer, Integer> pizzaIdAndQuantity = new HashMap<>();
	
	public OrderDTO() {
		
	}

	public Map<Integer, Integer> getPizzaIdAndQuantity() {
		return pizzaIdAndQuantity;
	}

	public void setPizzaIdAndQuantity(Map<Integer, Integer> pizzaIdAndQuantity) {
		this.pizzaIdAndQuantity = pizzaIdAndQuantity;
	}
	
	
}
