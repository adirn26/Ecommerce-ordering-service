package com.adirn.customerservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.customerservice.kafka.CustomerProducer;
import com.adirn.customerservice.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepo repo;

    @Autowired
    CustomerProducer customerProducer;

    @PostMapping("/register/customer")
    public ResponseEntity<?> register(@RequestBody Customer customer){
        repo.save(customer);
        customerProducer.sendMessage(customer);
        return new ResponseEntity<>("inserted customer ...", HttpStatus.OK);
    }

    @GetMapping("/customer")
    public ResponseEntity<?> getall(){
        List<Customer> customers = repo.findAll();
        return  new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<?> getbyId(@PathVariable int id){
        Customer customer = repo.findById(id).get();
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
    }
}
