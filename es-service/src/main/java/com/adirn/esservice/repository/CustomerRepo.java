package com.adirn.esservice.repository;

import com.adirn.esservice.model.CustomerES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends ElasticsearchRepository<CustomerES, Integer> {

}
