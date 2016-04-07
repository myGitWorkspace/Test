package ua.deliveryservice.repository;

import java.util.List;

import ua.deliveryservice.domain.Country;

public interface CountryRepository {
	
	List<Country> findAll();
	
	Country save(Country country);	
	
	void delete(Country country);
}
