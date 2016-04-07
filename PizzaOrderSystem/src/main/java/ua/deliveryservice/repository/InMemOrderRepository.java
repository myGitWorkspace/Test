package ua.deliveryservice.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.User;
import ua.deliveryservice.domain.Order.State;

@Repository
public class InMemOrderRepository implements OrderRepository {

	private final List<Order> orders = new ArrayList<>();
	
	public InMemOrderRepository() {
		
	}
	
	@Override
	public Order save(Order order) {
		orders.add(order);
		return order;
	}
	
	@Override
	public boolean equals(Object object2) {
		if(object2 == null)
			return false;
		if(object2.getClass() != this.getClass())
			return false;
		InMemOrderRepository ob2 = (InMemOrderRepository)object2;
		if(ob2.orders == null || ob2.orders != this.orders)
			return false;
		return true;		
	}
	
	@Override
	public void delete(Order order) {
	}
	
	@Override
	public Order find(Integer id) {
		return null;
	}
	
	@Override
	public List<Order> findAll() {
		return null;
	}
	
	@Override
	public int hashCode() {
		return orders.hashCode();
	}
	
	@Override
	public void updateState(Integer id, State state) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public List<Order> findByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void updateReleaseDate(Integer id, Date releaseDate) {
		// TODO Auto-generated method stub
		
	}
}
