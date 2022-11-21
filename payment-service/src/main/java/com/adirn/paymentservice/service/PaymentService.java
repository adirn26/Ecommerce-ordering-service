package com.adirn.paymentservice.service;

import com.adirn.basemodels.models.Payment;
import com.adirn.paymentservice.kafka.PaymentProducer;
import com.adirn.paymentservice.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    PaymentRepo productRepo;

    @Autowired
    PaymentProducer paymentProducer;

    public void savePayment(Payment payment){
        payment.setTransId(UUID.randomUUID().toString());
        payment.setStatus("Transaction completed");
        productRepo.save(payment);
        paymentProducer.sendMessage(payment);
    }

    public List<Payment> getAll(){
        return productRepo.findAll();
    }
}
