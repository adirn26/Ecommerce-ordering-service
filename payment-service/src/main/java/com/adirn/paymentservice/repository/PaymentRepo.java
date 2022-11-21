package com.adirn.paymentservice.repository;

import com.adirn.basemodels.models.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepo extends MongoRepository<Payment, String > {
}
