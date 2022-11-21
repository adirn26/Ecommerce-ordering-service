package com.adirn.paymentservice.controller;

import com.adirn.basemodels.models.Payment;
import com.adirn.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {

    @Autowired
    PaymentService paymentService;



    @PostMapping("/pay")
    public ResponseEntity<?> postPayment(@RequestBody Payment payment){
        paymentService.savePayment(payment);
        //payment gateway implementation
        return new ResponseEntity<>(String.format("Payment complete successfully ! TransId => %s", payment.getTransId()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/pay")
    public ResponseEntity<?>  getAll(){
        List<Payment> payments = paymentService.getAll();
        return new ResponseEntity<>(payments, HttpStatus.OK);

    }
}
