package com.falcon.clientbank.dao;

import com.falcon.clientbank.enteties.Account;
import com.falcon.clientbank.enteties.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDao implements Dao<Customer>{
    private List<Customer> customers = new ArrayList<>();

    @Override
    public Customer save(Customer customer) {
        customers.add(customer);
        return customer;
    }

    public boolean update(Customer customer) {
        customers.stream().filter(el -> el.getId().equals(customer.getId())).findFirst().ifPresent(el -> {
            el.setName(customer.getName());
            el.setEmail(customer.getEmail());
            el.setAge(customer.getAge());
        });

        return true;
    }

    @Override
    public boolean delete(Customer customer) {
        return customers.remove(customer);
    }

    @Override
    public void deleteAll(List<Customer> customers) {

    }

    @Override
    public void saveAll(List<Customer> customers) {

    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Customer getOne(long id) {
        return customers.get((int) id);
    }

    public Account createAccount(Account account, Long id) {
        customers.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst()
                .ifPresent(el -> el.getAccounts().add(account));
        return account;
    }

    public boolean deleteAccount(Account account, Long id) {
        Optional<Customer> optionalCustomer = customers.stream()
                .filter(el -> el.getId().equals(id))
                .findFirst();
        return optionalCustomer.get().getAccounts().remove(account);
    }
}