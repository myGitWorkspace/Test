package ua.deliveryservice.repository;

import java.util.Date;
import java.util.List;

import ua.deliveryservice.domain.User;

public interface UserRepository {

	User find(Integer id);
	
	List<User> findAll();
	
	User save(User user);
	
	void delete(User user);
	
	User findByLogin(String login);
	
	void updateLastVisitDate(Integer id, Date lastVisitDate);
	
	String findPassword(Integer id);
}