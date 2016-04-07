package ua.deliveryservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.Country;
import ua.deliveryservice.repository.CountryRepository;

@Service
public class SimpleCountryService implements CountryService {

	@Autowired
	private CountryRepository countryRepository;
	
	@Override
	@Transactional(readOnly=true)
	public List<Country> findAll() {
		return countryRepository.findAll();
	}
	
	@Override
	@Transactional
	public Country save(Country country) {
		return countryRepository.save(country);
	}
	
	@Override
	@Transactional
	public void delete(Country country) {
		countryRepository.delete(country);
	}
}
