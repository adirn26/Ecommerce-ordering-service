package com.adirn.esservice.kafka;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.Order;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.esservice.model.CustomerES;
import com.adirn.esservice.model.OrderEventES;
import com.adirn.esservice.repository.CustomerRepo;
import com.adirn.esservice.repository.OrderEventRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EsConsumer {

    private static final Logger logger = LoggerFactory.getLogger(EsConsumer.class);

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderEventRepo orderEventRepo;
    @Autowired
    private Tracer tracer;

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Customer customer){
        Span span = tracer.currentSpan();
        logger.info(String.format("traceId=%s Customer object recieved in ES service => %s", span.context().traceId(),customer.toString()));
        //save the email to the customer
        CustomerES customerES = new CustomerES();
        customerES.setId(customer.getId());
        customerES.setName(customer.getName());
        customerES.setEmail(customer.getEmail());
        customerES.setAge(customer.getAge());
        customerRepo.save(customerES);
        logger.info("Saved customer to es db");
    }

    @KafkaListener(topics = "${order.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){
        Span span = tracer.currentSpan();
        logger.info(String.format("traceId=%s Order event received in ES service => %s",span.context().traceId(), orderEvent.toString()));
        //save the email to the customer
        OrderEventES orderEventES = new OrderEventES();
        orderEventES.setMessage(orderEvent.getMessage());
        orderEventES.setOrder(orderEvent.getOrder());
        orderEventES.setProduct(orderEvent.getProduct());
        orderEventES.setId(orderEvent.getOrder().getId());
        orderEventES.setCustomer(orderEvent.getCustomer());
        orderEventES.setStatus(orderEvent.getStatus());
        orderEventRepo.save(orderEventES);
        logger.info("Saved Order event to es DB");

    }

}
