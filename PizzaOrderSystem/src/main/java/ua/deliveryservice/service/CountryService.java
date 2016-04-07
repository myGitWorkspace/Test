package ua.deliveryservice.service;

import java.util.List;

import ua.deliveryservice.domain.Country;

public interface CountryService {
	
	List<Country> findAll();
	
	Country save(Country country);	
	
	void delete(Country country);
}
