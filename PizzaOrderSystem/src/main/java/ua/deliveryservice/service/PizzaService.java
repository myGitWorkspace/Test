package ua.deliveryservice.service;

import java.util.List;

import ua.deliveryservice.domain.Pizza;

public interface PizzaService {

	Pizza find(Integer id);	
	
	List<Pizza> findAll();
	
	Pizza save(Pizza pizza);	
	
	void delete(Integer id);
}
