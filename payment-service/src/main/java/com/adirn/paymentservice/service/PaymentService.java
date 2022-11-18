package com.adirn.paymentservice.service;

import com.adirn.basemodels.models.Payment;
import com.adirn.paymentservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    ProductRepo productRepo;

    public void savePayment(Payment payment){
        payment.setTransId(UUID.randomUUID().toString());
        productRepo.save(payment);
    }
}
