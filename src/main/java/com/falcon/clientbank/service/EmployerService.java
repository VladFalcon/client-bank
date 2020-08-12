package com.falcon.clientbank.service;

import com.falcon.clientbank.dao.EmployerDao;
import com.falcon.clientbank.enteties.Employer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployerService {
    @Autowired
    private EmployerDao employerDao;

    public Employer save(Employer employer) {
        return employerDao.save(employer);
    }
    public Employer update(long id, Employer employerCandidate){
        if(employerCandidate.getNumber() != null
                && employerCandidate.getNumber() != null
        ){
            Employer employer =  this.getOne(id);
            if(employer == null) {
                return null;
            }
            employer.setAddress(employerCandidate.getAddress());
            employer.setNumber(employerCandidate.getNumber());
            return employerDao.save(employer);
        }
        return employerCandidate;
    }

    public boolean delete(long id) {
        Employer employer = this.getOne(id);
        employer.getCustomers()
                .forEach(customer -> customer.getEmployers()
                        .removeIf(el -> el.getId() == employer.getId()));
        employer.getCustomers().clear();
        employerDao.delete(employer);
        return true;
    }

    public void deleteAll(List<Employer> employers) {
        employerDao.deleteAll(employers);
    }

    public void saveAll(List<Employer> employers) {
        employerDao.saveAll(employers);
    }

    public List<Employer> findAll() {
        return employerDao.findAll();
    }

    public Employer getOne(long id) {
        return employerDao.getOne(id);
    }
}