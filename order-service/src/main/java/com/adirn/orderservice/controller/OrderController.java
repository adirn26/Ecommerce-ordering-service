package com.adirn.orderservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.Order;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.basemodels.models.Product;
import com.adirn.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class OrderController {

    @Value("${adirn.customer.uri}")
    private String customerUri;

    @Value("${adirn.product.uri}")
    private String productUri;

    @Autowired
    OrderProducer orderProducer;

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order){
        order.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("order status is in pending state");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);

        RestTemplate restTemplate = new RestTemplate();

        //getting customer data
        Map<String,Integer> uriparam = new HashMap<>();
        uriparam.put("id", order.getCid());
        Customer customerRes = restTemplate.getForObject(customerUri,Customer.class, uriparam);
        orderEvent.setCustomer(customerRes);

        //getting Product data
        Map<String, Integer> productparam = new HashMap<>();
        productparam.put("id", order.getPid());
        Product product = restTemplate.getForObject(productUri, Product.class, productparam);
        orderEvent.setProduct(product);

        orderProducer.sendMessage(orderEvent);
        return "Order Placed ......";
    }
}
