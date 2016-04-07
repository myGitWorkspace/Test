package ua.deliveryservice.repository;

import java.util.List;

import ua.deliveryservice.domain.Role;

public interface RoleRepository {
	
	List<Role> findAll();
	
	Role findByRoleName(String name);
	
	Role save(Role role);	
	
	void delete(Role role);
}
