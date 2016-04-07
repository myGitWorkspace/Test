package ua.deliveryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.repository.PizzaRepository;

@Service
public class SimplePizzaService implements PizzaService {
	
	private final PizzaRepository pizzaRepository;
	
	@Autowired
	public SimplePizzaService(@Qualifier("JPAPizzaRepository") PizzaRepository pizzaRepository) {
		this.pizzaRepository = pizzaRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Pizza find(Integer id) {
		return pizzaRepository.find(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pizza> findAll() {		
		return pizzaRepository.findAll();
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {		
		pizzaRepository.delete(id);
	}
	
	@Override
	@Transactional
	public Pizza save(Pizza pizza) {		
		return pizzaRepository.save(pizza);
	}
}
