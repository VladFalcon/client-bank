package com.falcon.clientbank.dao;


import com.falcon.clientbank.enteties.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
    public class EmployerDao implements Dao<Employer> {
    @Autowired
    private EntityManager em;
    @Override
    public Employer save(Employer employer) {
        return em.merge(employer);
    }

    @Override
    public void delete(Employer employer) {
        em.remove(employer);
    }

    @Override
    public void deleteAll(List<Employer> employers) {
        em.remove(employers);
    }

    @Override
    public void saveAll(List<Employer> employers) {
        em.persist(employers);
    }

    @Override
    public List<Employer> findAll() {
        return em.createNamedQuery("employers.findAll", Employer.class).getResultList();
    }

    @Override
    public boolean deleteById(long id) {
        return false;
    }

    @Override
    public Employer getOne(long id) {
        return em.find(Employer.class, id);
    }
}