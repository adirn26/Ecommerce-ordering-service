package com.adirn.basemodels.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Payment {
    @Id
    String transId;
    String status;
    String orderId;
    double price;
}
