package com.falcon.clientbank.dao;

import com.falcon.clientbank.enteties.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDao implements Dao<Customer> {

    @Autowired
    private EntityManager em;

    @Override
    public Customer save(Customer customer) {
        return em.merge(customer);
    }

    @Override
    public void delete(Customer customer) {
        em.remove(customer);
    }

    @Override
    public void deleteAll(List<Customer> customers) {
        em.remove(customers);
    }

    @Override
    public void saveAll(List<Customer> customers) {
        em.merge(customers);
    }

    @Override
    public List<Customer> findAll() {
        return em.createNamedQuery("customers.findAll", Customer.class).getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }


    @Override
    public Customer getOne(long id) {
        return em.find(Customer.class, id);
    }
}