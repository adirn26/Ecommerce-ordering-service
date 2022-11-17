package com.adirn.esservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.esservice.model.CustomerES;
import com.adirn.esservice.model.OrderEventES;
import com.adirn.esservice.repository.CustomerRepo;
import com.adirn.esservice.repository.OrderEventRepo;
import com.adirn.esservice.service.QueryDslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CustomerRepo repository;

    @Autowired
    private OrderEventRepo orderEventRepo;

    @Autowired
    QueryDslService queryDslService;

    @PostMapping("/customer/saveCustomer")
    public int saveCustomer(@RequestBody List<CustomerES> customers) {
        repository.saveAll(customers);
        return customers.size();
    }

    @GetMapping("/customer/findAll")
    public Iterable<CustomerES> findAllCustomers() {
        return repository.findAll();
    }


    @GetMapping("/orders/findAll")
    public Iterable<OrderEventES> findAllOrders(){
        return orderEventRepo.findAll();
    }

    @GetMapping("/customer/findby/{text}")
    public List<CustomerES> getbytext(@PathVariable String text){
        List<CustomerES> customers = queryDslService.multiMatch(text);
        return customers;
    }
}
