package com.adirn.customerservice.service;

import com.adirn.basemodels.models.Customer;

import java.util.List;

public interface CustomerService {

    public void createCustomer(Customer customer);

    public List<Customer> getAllCust();

    public Customer getCust(int id);
}
