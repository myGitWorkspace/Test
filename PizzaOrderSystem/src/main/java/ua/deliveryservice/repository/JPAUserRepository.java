package ua.deliveryservice.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.deliveryservice.domain.User;

@Repository
public class JPAUserRepository implements UserRepository {

    @PersistenceContext
    private EntityManager em;
	
	@Override	
	public User find(Integer id) {		
		return em.find(User.class, id);
	}
	
	@Override
	public List<User> findAll() {		
		return em.createNamedQuery("User.findAll", User.class).getResultList();
	}
	
	@Override	
	public User save(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
	}
	
	@Override	
	public void delete(User user) {
		em.remove(em.contains(user) ? user : em.merge(user));
	}
	
	@Override
	public User findByLogin(String login) {
		Query query = em.createNamedQuery("User.findByLogin", User.class);
		query.setParameter("login", login);
		List<User> list = query.getResultList();
		return list.size() == 0 ? null : (User)list.get(0);
	}
	
	@Override
	public void updateLastVisitDate(Integer id, Date lastVisitDate) {
		Query query = em.createNamedQuery("User.updateLastVisitDate");
		query.setParameter("id", id);
		query.setParameter("lastVisitDate", lastVisitDate);		
		query.executeUpdate();
	}
	
	@Override
	public String findPassword(Integer id) {
		Query query = em.createNamedQuery("User.findPassword");
		query.setParameter("id", id);
		return (String)query.getSingleResult();
	}
	
}
