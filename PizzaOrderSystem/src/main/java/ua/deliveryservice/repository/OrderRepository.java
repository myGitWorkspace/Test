package ua.deliveryservice.repository;

import java.util.Date;
import java.util.List;

import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.User;


public interface OrderRepository {
	
	Order find(Integer id);
	
	List<Order> findAll();
	
	List<Order> findByUser(User user);
	
	Order save(Order order);
	
	void delete(Order order);
	
	void updateState(Integer id, Order.State state);
	
	void updateReleaseDate(Integer id, Date releaseDate);
}
