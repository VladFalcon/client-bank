package com.falcon.clientbank.controller;

import com.falcon.clientbank.enteties.Account;
import com.falcon.clientbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/account")
    List<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }

    @PostMapping("/accountTopUp")
    boolean replenishAccountBalance(Long id, Double sum){
        return accountService.topUpAccountBalance(id, sum);
    }

    @PostMapping("/accountWithdraw")
    boolean takeOffFromAccountBalance(Long id, Double sum){
        return accountService.withdrawFromAccountBalance(id, sum);
    }

    @PostMapping("/accountTransfer")
    boolean transferMoneyFromAccountToAccount(Long id1, Long id2, Double sum){
        return accountService.transferFromAccountToAccount(id1, id2, sum);
    }
}