package ua.deliveryservice.repository;

import java.util.List;

import ua.deliveryservice.domain.PizzaType;

public interface PizzaTypeRepository {

	PizzaType find(Integer id);
	
	List<PizzaType> findAll();
	
	PizzaType save(PizzaType pizzaType);	
	
	void delete(Integer id);
}