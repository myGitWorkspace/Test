package ua.deliveryservice.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.annotations.Benchmark;
import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.Order.State;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.repository.OrderRepository;
import ua.deliveryservice.web.OrderController;


@Service
public class SimpleOrderService implements OrderService {
	
	private final OrderRepository orderRepository;
	private final PizzaService pizzaService;
	private final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	public SimpleOrderService(@Qualifier("JPAOrderRepository") OrderRepository orderRepository, PizzaService pizzaService) {
		this.orderRepository = orderRepository;
		this.pizzaService = pizzaService;
	}
	
	@Benchmark
	@Override
	@Transactional
	public Order placeNewOrder(User customer, Integer ... pizzasID) {
		
		List<Pizza> pizzas = new ArrayList<>();
		
		for(Integer id : pizzasID) {
			pizzas.add(findPizzaById(id));
		}
		
		Order newOrder = new Order(customer, pizzas);
		newOrder.setCreationDate(new Date());

		saveOrder(newOrder);
		
		return newOrder;
	}
	
	private Pizza findPizzaById(Integer id) {
		return pizzaService.find(id);
	}
	
    private void saveOrder(Order newOrder) {
        orderRepository.save(newOrder);
    }
   
    @Override
    @Transactional(readOnly = true)
    public Order find(Integer id) {    	
    	return orderRepository.find(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {    	
    	return orderRepository.findAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Order> findByUser(User user) {    	
    	return orderRepository.findByUser(user);
    }
    
    @Override
    @Transactional
    public void updateState(Integer id, State state) {
    	orderRepository.updateState(id, state);
    }
    
    @Override
    @Transactional
    public void updateReleaseDate(Integer id, Date releaseDate) {
    	orderRepository.updateReleaseDate(id, releaseDate);
    }
}
