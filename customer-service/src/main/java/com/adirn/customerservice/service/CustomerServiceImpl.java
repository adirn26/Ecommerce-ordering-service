package com.adirn.customerservice.service;

import com.adirn.basemodels.models.Customer;
import com.adirn.customerservice.repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements  CustomerService{

    @Autowired
    CustomerRepo repo;

    @Override
    public void createCustomer(Customer customer) {
        repo.save(customer);
    }

    @Override
    public List<Customer> getAllCust() {

        return repo.findAll();
    }

    @Override
    @Cacheable(value = Customer.HASH_VALUE, key = "#id")
    public Customer getCust(int id) {
        return repo.findById(id).get();
    }
}
