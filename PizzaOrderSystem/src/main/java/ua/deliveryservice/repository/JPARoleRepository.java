package ua.deliveryservice.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.Role;

@Repository
public class JPARoleRepository implements RoleRepository {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Role> findAll() {		
		return em.createNamedQuery("Role.findAll", Role.class).getResultList();
	}
	
	@Override
	public Role findByRoleName(String name) {		
		Query query = em.createQuery("Select r from Role r where name=:name", Role.class);
		query.setParameter("name", name);
		return (Role)query.getSingleResult();
	}	
	
	@Override
	public Role save(Role role) {
		if(role.getId() == null) {
			em.persist(role);
		} else {
			em.merge(role);
		}
		return role;
	}
	
	@Override
	public void delete(Role role) {
		em.remove(em.contains(role) ? role : em.merge(role));
	}
}
