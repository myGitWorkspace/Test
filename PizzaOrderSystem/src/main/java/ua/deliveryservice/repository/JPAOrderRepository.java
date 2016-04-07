package ua.deliveryservice.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.Order;
import ua.deliveryservice.domain.Order.State;
import ua.deliveryservice.domain.User;

@Repository(value="JPAOrderRepository")
public class JPAOrderRepository implements OrderRepository {
    @PersistenceContext
    private EntityManager em;
	
	@Override	
	public Order find(Integer id) {		
		return em.find(Order.class, id);
	}
	
	@Override	
	public Order save(Order order) {
        if (order.getId() == null) {
            em.persist(order);
        } else {
            em.merge(order);
        }
        return order;
	}
	
	@Override	
	public void delete(Order order) {
		em.remove(em.contains(order) ? order : em.merge(order));
	}
	
    @Override   
    public List<Order> findAll() {    	
    	return em.createNamedQuery("Order.findAll", Order.class).getResultList();
    }
    
    @Override
    public List<Order> findByUser(User user) {
    	Query query = em.createNamedQuery("Order.findByUser");
    	query.setParameter("user", user);
    	return query.getResultList();
    }
    
    @Override
    public void updateState(Integer id, State state) {
    	Query query = em.createNamedQuery("Order.updateState");
    	query.setParameter("id", id);
    	query.setParameter("state", state);    	
    	query.executeUpdate();
    }
    
    @Override
    public void updateReleaseDate(Integer id, Date releaseDate) {
    	Query query = em.createNamedQuery("Order.updateReleaseDate");
    	query.setParameter("id", id);
    	query.setParameter("releaseDate", releaseDate);    	
    	query.executeUpdate();    	
    }
    
}
