package ua.deliveryservice.service;

import java.util.List;

import ua.deliveryservice.domain.PizzaType;

public interface PizzaTypeService {
	
	PizzaType find(Integer id);	
	
	List<PizzaType> findAll();
	
	PizzaType save(PizzaType pizzaType);	
	
	void delete(Integer id);
}
