package com.falcon.clientbank.service;

import com.falcon.clientbank.dao.AccountDao;
import com.falcon.clientbank.enteties.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountDao accountDao;

    public List<Account> getAllAccounts(){
        return accountDao.findAll();
    }

    public boolean topUpAccountBalance(Long id, Double sum){
        Account thisAccount = (Account) accountDao.getOne(id);
        thisAccount.setBalance(thisAccount.getBalance() + sum);
        return true;
    }

    public boolean withdrawFromAccountBalance(Long id, Double sum){
        Account thisAccount = (Account) accountDao.getOne(id);
        if(thisAccount.getBalance() >= sum){
            thisAccount.setBalance(thisAccount.getBalance() - sum);
            return true;
        }
        return false;
    }

    public boolean transferFromAccountToAccount(Long id1, Long id2, Double sum){
        Account thisA1 = (Account) accountDao.getOne(id1);
        Account thisA2 = (Account) accountDao.getOne(id2);
        if(thisA1.getBalance() >= sum){
            thisA1.setBalance(thisA1.getBalance() - sum);
            thisA2.setBalance(thisA2.getBalance() + sum);
            return true;
        }
        return false;
    }
}

