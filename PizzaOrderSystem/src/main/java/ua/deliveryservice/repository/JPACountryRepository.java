package ua.deliveryservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.Country;

@Repository
public class JPACountryRepository implements CountryRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Country> findAll() { 
		return em.createNamedQuery("Country.findAll", Country.class).getResultList();
	}
	
	@Override
	public Country save(Country country) {
		if(country.getId() == null) {
			em.persist(country);
		} else {
			em.merge(country);
		}			
		return country;
	}
	
	@Override
	public void delete(Country country) {		
		em.remove(em.contains(country) ? country : em.merge(country));
	}
}
