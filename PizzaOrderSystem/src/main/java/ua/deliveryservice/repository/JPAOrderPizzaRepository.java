package ua.deliveryservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.OrderPizza;

@Repository
public class JPAOrderPizzaRepository implements OrderPizzaRepository  {

    @PersistenceContext
    private EntityManager em;
    
	@Override
	public List<OrderPizza> find() {		
		return em.createNamedQuery("OrderPizza.getOrderPizzaAll", OrderPizza.class).getResultList();

	}
}
