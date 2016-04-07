package ua.deliveryservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.PizzaType;

@Repository
public class JPAPizzaTypeRepository implements PizzaTypeRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public PizzaType find(Integer id) {
		return em.find(PizzaType.class, id);
	}
	
	@Override
	public List<PizzaType> findAll() {		
		return em.createNamedQuery("PizzaType.findAll", PizzaType.class).getResultList();
	}
	
	@Override
	public PizzaType save(PizzaType pizzaType) {
		if(pizzaType.getId() == null) {
			em.persist(pizzaType);
		} else {
			em.merge(pizzaType);
		}
		return pizzaType;
	}
	
	@Override
	public void delete(Integer id) {
		Query query = em.createNamedQuery("PizzaType.delete");
		query.setParameter("id", id);
		query.executeUpdate();
	}
}
