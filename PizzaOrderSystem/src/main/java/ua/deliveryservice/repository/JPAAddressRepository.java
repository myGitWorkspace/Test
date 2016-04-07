package ua.deliveryservice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import ua.deliveryservice.domain.Address;

public class JPAAddressRepository implements AddressRepository {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    @Transactional(readOnly = true)
    public Address find(Integer id) {       
        return em.find(Address.class, id);
    }

    @Override
    @Transactional
    public Address save(Address address) {
        if (address.getId() == null) {
            em.persist(address);
        } else {
            em.merge(address);
        }
        return address;
    }
    
}
