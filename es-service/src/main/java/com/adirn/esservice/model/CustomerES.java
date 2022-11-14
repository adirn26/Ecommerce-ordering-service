package com.adirn.esservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "customer")
public class CustomerES {

    @Id
    private int id;
    private String name;
    private String email;
    private int age;
}
