package ua.deliveryservice.service;

import java.util.Date;
import java.util.List;

import ua.deliveryservice.domain.Address;
import ua.deliveryservice.domain.Pizza;
import ua.deliveryservice.domain.User;

public interface UserService {

	//User createCustomer(Integer id, String name, Address address);
	
	User find(Integer id);	
	
	List<User> findAll();
	
	User save(User user);	
	
	void delete(User user);
	
	User findByLogin(String login);
	
	void updateLastVisitDate(Integer id, Date lastVisitDate);
	
	public String findPassword(Integer id);
}
