package ua.deliveryservice.repository;

import ua.deliveryservice.domain.Address;

public interface AddressRepository {

	Address find(Integer id);
	
	Address save(Address address);
}
