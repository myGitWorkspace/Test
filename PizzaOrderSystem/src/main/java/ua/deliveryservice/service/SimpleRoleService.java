package ua.deliveryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.Role;
import ua.deliveryservice.repository.RoleRepository;

@Service
public class SimpleRoleService implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Role> findAll() {
		return roleRepository.findAll();
	}
	
	@Override
	public Role findByRoleName(String name) {		
		return roleRepository.findByRoleName(name);
	}
	
	@Override
	@Transactional
	public Role save(Role role) {
		return roleRepository.save(role);
	}
	
	@Override
	@Transactional
	public void delete(Role role) {
		roleRepository.delete(role);
	}
	
}
