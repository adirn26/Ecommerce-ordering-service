package com.adirn.paymentservice.kafka;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.basemodels.models.Payment;
import com.adirn.paymentservice.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @Autowired
    private PaymentService paymentService;

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        logger.info(String.format("Order event received in payment service => %s", orderEvent.toString()));
        //complete the payment
    }

}
