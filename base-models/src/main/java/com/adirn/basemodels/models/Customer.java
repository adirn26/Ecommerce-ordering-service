package com.adirn.basemodels.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customer")
public class Customer implements Serializable {

    @Transient
    public static final String HASH_VALUE= "customer";
    @Id
    private int id;
    private String name;
    private String email;
    private int age;
}
