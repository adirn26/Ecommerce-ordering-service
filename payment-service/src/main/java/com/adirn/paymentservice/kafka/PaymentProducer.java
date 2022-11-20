package com.adirn.paymentservice.kafka;

import com.adirn.basemodels.models.OrderEvent;
import com.adirn.basemodels.models.Payment;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class PaymentProducer {
    private static final Logger logger = LoggerFactory.getLogger(PaymentProducer.class);

    @Autowired
    private NewTopic newTopic;

    @Autowired
    private KafkaTemplate<String, Payment> kafkaTemplate;

    public void sendMessage(Payment event){
        logger.info(String.format("Payment Event => %s", event.toString()));

        Message<Payment> message = MessageBuilder
                .withPayload(event)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
