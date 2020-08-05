package com.falcon.clientbank.dao;

import com.falcon.clientbank.enteties.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDao implements Dao<Account>{

    private final List<Account> accounts = new ArrayList<>();

    @Override
    public Account save(Account obj) {
        accounts.add((Account) obj);
        return (Account) obj;
    }

    @Override
    public boolean delete(Account obj) {
        return accounts.remove((Account) obj);
    }

    @Override
    public void deleteAll(List<Account> entities) {
        accounts.clear();
    }

    @Override
    public void saveAll(List<Account> entities) {
        accounts.containsAll(entities);
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }

    @Override
    public boolean deleteById(long id) {
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getId() == id){
                accounts.remove(accounts.get(i));
                return true;
            }
        }
        return false;
    }

    @Override
    public Account getOne(long id) {
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getId() == id){
                return accounts.get(i);
            }
        }
        return null;
    }
}