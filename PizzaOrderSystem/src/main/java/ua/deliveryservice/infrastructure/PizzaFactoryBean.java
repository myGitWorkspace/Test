package ua.deliveryservice.infrastructure;

import org.springframework.beans.factory.FactoryBean;

import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.PizzaType;

public class PizzaFactoryBean implements FactoryBean<Pizza> {

	private Integer id;
	private String name;
	private PizzaType pizzaType;
	private Double price;
		
	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(PizzaType pizzaType) {
		this.pizzaType = pizzaType;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public Pizza getObject() throws Exception {
		return new Pizza(id, name, pizzaType, price);
	}
	
	@Override
	public Class<?> getObjectType() {
		return Pizza.class;
	}
	
	@Override
	public boolean isSingleton() {
		return true;
	}
}
