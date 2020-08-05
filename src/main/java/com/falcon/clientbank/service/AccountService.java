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

    public boolean transferFromAccountToAccount(Long idFrom, Long idTo, Double sum){
        Account accountFrom = (Account) accountDao.getOne(idFrom);
        Account accountTo = (Account) accountDao.getOne(idTo);
        if(accountFrom.getBalance() >= sum){
            accountFrom.setBalance(accountFrom.getBalance() - sum);
            accountTo.setBalance(accountTo.getBalance() + sum);
            return true;
        }
        return false;
    }
}

