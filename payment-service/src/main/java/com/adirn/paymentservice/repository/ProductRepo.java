package com.adirn.paymentservice.repository;

import com.adirn.basemodels.models.Payment;
import com.adirn.basemodels.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Payment, String > {
}
