package com.falcon.clientbank.dao;

import com.falcon.clientbank.enteties.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public  class AccountDao implements Dao<Account> {

    @Autowired
    private EntityManager em;

    @Override
    public Account save(Account account) {
        return em.merge(account);
    }

    @Override
    public void delete(Account account) {
        em.remove(account);
    }

    @Override
    public void deleteAll(List<Account> accounts) {
//        em.createNamedQuery("accounts.deleteAll").executeUpdate();
    }

    @Override
    public void saveAll(List<Account> accounts) {
        em.persist(accounts);
    }

    @Override
    public List<Account> findAll() {
        return em.createNamedQuery("accounts.findAll", Account.class).getResultList();

    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }


    @Override
    public Account getOne(long id) {
        return em.find(Account.class, id);
    }

    public Account getByNumber(String number){
        return em.createNamedQuery("accounts.findByNumber", Account.class)
                .setParameter("number", number).getSingleResult();
    }
}
