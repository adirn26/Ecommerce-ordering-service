package com.adirn.esservice.model;

import com.adirn.basemodels.models.Customer;
import com.adirn.basemodels.models.Order;
import com.adirn.basemodels.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "order")
public class OrderEventES {
    @Id
    private String id;
    private String status;
    private String message;
    private Customer customer;
    private Order order;
    private Product product;
}
