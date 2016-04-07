package ua.deliveryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.PizzaType;
import ua.deliveryservice.repository.PizzaTypeRepository;

@Service
public class SimplePizzaTypeService implements PizzaTypeService {

	@Autowired
	private PizzaTypeRepository pizzaTypeRepository;
	
	@Override
	@Transactional(readOnly=true)
	public PizzaType find(Integer id) {		
		return pizzaTypeRepository.find(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<PizzaType> findAll() {		
		return pizzaTypeRepository.findAll();
	}
	
	@Override
	@Transactional
	public PizzaType save(PizzaType pizzaType) {
		return pizzaTypeRepository.save(pizzaType);
	}
	
	@Override
	@Transactional
	public void delete(Integer id) {
		pizzaTypeRepository.delete(id);
	}
}
