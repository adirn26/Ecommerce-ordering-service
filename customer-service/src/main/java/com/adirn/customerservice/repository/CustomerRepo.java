package com.adirn.customerservice.repository;

import com.adirn.basemodels.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer, Integer> {
}
