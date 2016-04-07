package ua.deliveryservice.service;

import java.util.List;

import ua.deliveryservice.domain.Role;

public interface RoleService {
		
	List<Role> findAll();
	
	Role findByRoleName(String name);
	
	Role save(Role role);	
	
	void delete(Role role);
}
