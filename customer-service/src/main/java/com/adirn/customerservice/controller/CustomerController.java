package com.adirn.customerservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.customerservice.kafka.CustomerProducer;
import com.adirn.customerservice.repository.CustomerRepo;
import com.adirn.customerservice.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerRepo repo;

    @Autowired
    CustomerProducer customerProducer;

    @Autowired
    CustomerService customerService;


    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        customerService.createCustomer(customer);
        customerProducer.sendMessage(customer);
        return new ResponseEntity<>("inserted customer ...", HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getall(){
        List<Customer> customers = customerService.getAllCust();
        return  new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id){
        Customer customer = customerService.getCust(id);
        logger.info(customer.toString());
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }


}
