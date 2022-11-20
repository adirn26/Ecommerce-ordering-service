package com.adirn.esservice.kafka;

import com.adirn.basemodels.models.Payment;
import com.adirn.esservice.model.OrderEventES;
import com.adirn.esservice.service.QueryDslService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentConsumer {

    private static final Logger logger = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    QueryDslService queryDslService;


    @KafkaListener(topics = "${payment.kafka.topic.name}",
                  groupId = "${spring.kafka.consumer.group-id}")
    public void consume(Payment payment){
        logger.info(String.format("payment event received in ES service => %s", payment.toString()));
        OrderEventES orderEventES = queryDslService.updateOrder(payment.getOrderId());
        logger.info(String.format("Updated order => %s ", orderEventES.toString()));
    }

}
