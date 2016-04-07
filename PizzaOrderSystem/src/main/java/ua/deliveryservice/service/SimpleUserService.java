package ua.deliveryservice.service;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.User;
import ua.deliveryservice.repository.UserRepository;

@Service
public class SimpleUserService implements UserService {
	
	private final UserRepository userRepository;
	
	@Autowired
	public SimpleUserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public User find(Integer id) {		
		return userRepository.find(id);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	@Override
	@Transactional
	public User save(User user) {		
		return userRepository.save(user);
	}
	
	@Override
	@Transactional
	public void delete(User user) {
		userRepository.delete(user);
	}
	
	@Override
	@Transactional(readOnly = true)
	public User findByLogin(String login) {
		return userRepository.findByLogin(login);
	}
	
	@Override
	@Transactional
	public void updateLastVisitDate(Integer id, Date lastVisitDate) {		
		userRepository.updateLastVisitDate(id, lastVisitDate);
	}
	
	@Override
	public String findPassword(Integer id) {
		return userRepository.findPassword(id);
	}
}
