package com.workintech.controller;

import com.workintech.dto.CustomerResponse;
import com.workintech.entity.Customer;
import com.workintech.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    public final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer c) {
        Customer saved = this.customerService.save(c);
        return new CustomerResponse(saved.getId(), saved.getEmail(), saved.getSalary());
    }
}
