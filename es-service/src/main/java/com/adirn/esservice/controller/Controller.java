package com.adirn.esservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.esservice.model.CustomerES;
import com.adirn.esservice.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CustomerRepo repository;

    @PostMapping("/saveCustomer")
    public int saveCustomer(@RequestBody List<CustomerES> customers) {
        repository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/findAll")
    public Iterable<CustomerES> findAllCustomers() {
        return repository.findAll();
    }
}
