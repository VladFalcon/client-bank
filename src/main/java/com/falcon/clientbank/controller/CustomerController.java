package com.falcon.clientbank.controller;

import com.falcon.clientbank.enteties.Account;
import com.falcon.clientbank.enteties.Customer;
import com.falcon.clientbank.enteties.Employer;
import com.falcon.clientbank.service.AccountService;
import com.falcon.clientbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/" + ApiConstants.API_VERSION + "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.findAll();
    }
    @PostMapping
    public Customer saveCustomer(@RequestBody Customer customerCandidate, HttpServletResponse response){
        Customer customer = customerService.save(customerCandidate);
        if(customer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else{
            response.setStatus(HttpServletResponse.SC_CREATED);
        }
        return customer;
    }
    @DeleteMapping
    public void deleteAllCustomers(@RequestBody List<Customer> customers){
        customerService.deleteAll(customers);
    }
    @GetMapping(value = "/{id}")
    public Customer getCustomerById(@PathVariable("id") long id, HttpServletResponse response){
        Customer customer = customerService.getOne(id);
        if(customer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        return customer;
    }
    @PutMapping("/{id}")
    public Customer updateCustomer(@RequestBody Customer customerCandidate, @PathVariable("id") long id,
                                   HttpServletResponse response){
        Customer customer = customerService.update(id, customerCandidate);
        if(customer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        else if(customer == customerCandidate){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
        }
        return customer;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteCustomerById(@PathVariable("id") long id, HttpServletResponse response){
        boolean isDelete = customerService.deleteById(id);
        if(isDelete == false) response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @GetMapping(value = "/{id}/accounts")
    public List<Account> getCustomerAccounts(@PathVariable("id") long id){
        return customerService.getAccounts(id);
    }
    @PostMapping(value = "/{id}/accounts")
    public Account addCustomerAccount(@PathVariable("id") long id,
                                      @RequestBody Account account,
                                      HttpServletResponse response){
        Account addedAccount = customerService.addCustomerAccount(id, account);
        if (addedAccount == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return addedAccount;
    }
    @DeleteMapping(value = "/{id}/accounts/{accountId}")
    public void deleteCustomerAccount(@PathVariable("id") long id,
                                      @PathVariable("accountId") long accountId,
                                      HttpServletResponse response){
        boolean isDeleted = accountService.delete(accountId);
        if(!isDeleted) response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    }

    @PostMapping(value = "/{id}/employers")
    public Customer addCustomerEmployer(@PathVariable("id") long id,
                                        @RequestBody Employer employer,
                                        HttpServletResponse response){
        Customer customer = customerService.addCustomerEmployer(id, employer);
        if(customer == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
        response.setStatus(HttpServletResponse.SC_CREATED);
        return customer;

    }
}
