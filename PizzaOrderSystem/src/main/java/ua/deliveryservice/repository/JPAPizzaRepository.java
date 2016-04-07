package ua.deliveryservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.Pizza;

@Repository(value="JPAPizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Pizza find(Integer id) {       
        return em.find(Pizza.class, id);
    }

    @Override
    public Pizza save(Pizza pizza) {
        if (pizza.getId() == null) {
            em.persist(pizza);
        } else {
            em.merge(pizza);
        }
        return pizza;
    }
    
    @Override
    public List<Pizza> findAll() {    	
    	return em.createNamedQuery("Pizza.findAll", Pizza.class).getResultList();
    }
    
    @Override
    public void delete(Integer id) {
    	Query query = em.createNamedQuery("Pizza.delete");
    	query.setParameter("id", id);
    	query.executeUpdate();	
    }
}
