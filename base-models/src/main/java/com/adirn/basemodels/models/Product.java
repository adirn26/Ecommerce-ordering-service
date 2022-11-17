package com.adirn.basemodels.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Transient
    public String HASH_VALUE= "product";
    @Id
    private int id;
    private String name;
    private double price;
    private int qtyavail;
}
