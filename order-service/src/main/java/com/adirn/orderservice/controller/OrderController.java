package com.adirn.orderservice.controller;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.Order;
import com.adirn.basemodels.models.OrderEvent;
import com.adirn.basemodels.models.Product;
import com.adirn.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) throws Exception {
        order.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setMessage("Complete the payment");
        orderEvent.setStatus("PENDING");
        orderEvent.setOrder(order);

//        RestTemplate restTemplate = new RestTemplate();

        //getting customer data
        Map<String,Integer> uriparam = new HashMap<>();
        uriparam.put("id", order.getCid());
//        Customer customerRes = restTemplate.getForObject(customerUri,Customer.class, uriparam);
        ResponseEntity<?> customerRes = restTemplate.getForEntity(customerUri, Customer.class, uriparam);
        if(customerRes.getStatusCode()!= HttpStatus.OK){
            throw new Exception((String) customerRes.getBody());
        }
        orderEvent.setCustomer((Customer) customerRes.getBody());

        //getting Product data
        Map<String, Integer> productparam = new HashMap<>();
        productparam.put("id", order.getPid());
//        Product product = restTemplate.getForObject(productUri, Product.class, productparam);
        ResponseEntity<?> product = restTemplate.getForEntity(productUri, Product.class, productparam);
        if(product.getStatusCode()!=HttpStatus.OK){
            throw new Exception((String) product.getBody());
        }
        orderEvent.setProduct((Product) product.getBody());

        orderProducer.sendMessage(orderEvent);
        return "Order Placed successfully ! Your orderId => "+order.getId();
    }
}
