package com.workintech.controller;

import com.workintech.dto.AccountResponse;
import com.workintech.dto.CustomerResponse;
import com.workintech.entity.Account;
import com.workintech.entity.Customer;
import com.workintech.service.AccountService;
import com.workintech.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    private final CustomerService customerService;

    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account find(@PathVariable long id) {
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public AccountResponse save(@RequestBody Account account, @PathVariable long customerId) {
        Customer customer = customerService.find(customerId);
        if (customer != null) {
            customer.getAccounts().add(account);
            account.setCustomer(customer);
            accountService.save(account);
        }
        else {
            throw new RuntimeException("No customer found");
        }
        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));
    }

    @PutMapping("/{customerId}")
    public AccountResponse update(@RequestBody Account account, @PathVariable long customerId) {
        Customer customer = customerService.find(customerId);
        Account foundAccount = null;
        for (Account account1 : customer.getAccounts()) {
            if (account.getId() == account1.getId()) {
                foundAccount = account1;
            }
        }
        if (foundAccount == null) {
            throw new RuntimeException("Account (" + account.getId() + ") not found for this cutomer: " + customerId);
        }

        int indexOfFoundAccount = customer.getAccounts().indexOf(foundAccount);
        customer.getAccounts().set(indexOfFoundAccount, account);
        account.setCustomer(customer);
        accountService.save(account);

        return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                new CustomerResponse(customer.getId(), customer.getEmail(), customer.getSalary()));
    }

    @DeleteMapping("/{id}")
    public AccountResponse remove (@PathVariable long id) {
        Account account = accountService.find(id);
        if (account != null) {
            accountService.delete(id);
            return new AccountResponse(account.getId(), account.getAccountName(), account.getMoneyAmount(),
                    new CustomerResponse(account.getCustomer().getId(), account.getCustomer().getEmail(),
                            account.getCustomer().getSalary()));
        } else {
            throw new RuntimeException("No account found");
        }
    }
}
