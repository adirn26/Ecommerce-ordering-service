package com.adirn.emailservice.kafka;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.basemodels.models.Payment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);


    @Autowired
    RestTemplate restTemplate;

    @Value("${adirn.esservice.uri}")
    private String esuri;

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Customer customer){
        logger.info(String.format("Customer object recieved in email service => %s", customer.toString()));
        //save the email to the customer
    }

    @KafkaListener(topics = "${order.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        logger.info(String.format("Order event received in email service => %s", orderEvent.toString()));
        //save the email to the customer
    }

    @KafkaListener(topics = "${payment.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Payment payment){
        logger.info(String.format("Payment event received in email service => %s", payment.toString()));
        //get customer details
        Map<String,String> uriparam = new HashMap<>();
        uriparam.put("id", payment.getOrderId());
        ResponseEntity<?> response = restTemplate.getForEntity(esuri,Customer.class, uriparam);
        if(response.getStatusCode() == HttpStatus.OK){
            logger.info(String.format("Send reciept to customer => %s", response.getBody().toString()));
            //email sender implementation
        }
        else{
            logger.error((String) response.getBody());
        }
    }
}
