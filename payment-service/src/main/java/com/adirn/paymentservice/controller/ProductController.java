package com.adirn.paymentservice.controller;

import com.adirn.basemodels.models.Payment;
import com.adirn.basemodels.models.Product;
import com.adirn.paymentservice.kafka.PaymentProducer;
import com.adirn.paymentservice.repository.ProductRepo;
import com.adirn.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    PaymentService paymentService;



    @PostMapping("/pay")
    public ResponseEntity<?> postPayment(@RequestBody Payment payment){
        paymentService.savePayment(payment);
        //payment gateway implementation
        return new ResponseEntity<>(String.format("Payment complete successfully ! TransId => %s", payment.getTransId()), HttpStatus.ACCEPTED);
    }
}
