package com.adirn.esservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.esservice.model.CustomerES;
import com.adirn.esservice.model.OrderEventES;
import com.adirn.esservice.repository.CustomerRepo;
import com.adirn.esservice.repository.OrderEventRepo;
import com.adirn.esservice.service.QueryDslService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.BooleanOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/orders/customer/{id}")
    public ResponseEntity<?> getOrder(@PathVariable String id){
        Optional<OrderEventES> order =  orderEventRepo.findById(id);
        if(order.isEmpty()){
            return new ResponseEntity<>("Invalid id", HttpStatus.BAD_REQUEST);
        }
        else{
            Customer customer = order.get().getCustomer();
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }

//    @PutMapping("/orders/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable String id){
//        OrderEventES orderEvent =  orderEventRepo.findById(id).get();
//        orderEvent.setStatus("SUCCESSFULL");
//        orderEvent.setMessage("ORDER PLACED.......");
//        orderEventRepo.save(orderEvent);
//        return new ResponseEntity<>("Succesfull...", HttpStatus.OK);
//    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        OrderEventES orderEventES=orderEventRepo.findById(id).get();
        return new ResponseEntity<>(orderEventES, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id){
        orderEventRepo.deleteById(id);
        return new ResponseEntity<>("Deleted order ....", HttpStatus.OK);
    }

    @DeleteMapping("/orders")
    public ResponseEntity<?> deleteAll(){
        orderEventRepo.deleteAll();
        return new ResponseEntity<>("All orders deleted....", HttpStatus.OK);
    }
}
