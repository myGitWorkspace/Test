package ua.deliveryservice.service;

import java.util.Date;
import java.util.List;

import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.domain.Order.State;

public interface OrderService {
	
	Order placeNewOrder(User customer, Integer... pizzaID);	
	
	Order find(Integer id);
	
	List<Order> findAll();
	
	List<Order> findByUser(User user);
	
	void updateState(Integer id, State state);
		
	void updateReleaseDate(Integer id, Date releaseDate);
}
