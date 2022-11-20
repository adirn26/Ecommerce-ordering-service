package com.adirn.paymentservice.service;

import com.adirn.basemodels.models.Payment;
import com.adirn.paymentservice.kafka.PaymentProducer;
import com.adirn.paymentservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PaymentProducer paymentProducer;

    public void savePayment(Payment payment){
        payment.setTransId(UUID.randomUUID().toString());
        payment.setStatus("Transaction completed");
        productRepo.save(payment);
        paymentProducer.sendMessage(payment);
    }
}
