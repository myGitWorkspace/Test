package ua.deliveryservice.repository;

import java.util.List;

import ua.deliveryservice.domain.Pizza;


public interface PizzaRepository {
	
	Pizza find(Integer id);
	
	List<Pizza> findAll();
	
	Pizza save(Pizza pizza);	
	
	void delete(Integer id);
}
