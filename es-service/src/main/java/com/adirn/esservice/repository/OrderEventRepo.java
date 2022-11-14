package com.adirn.esservice.repository;

import com.adirn.esservice.model.OrderEventES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderEventRepo extends ElasticsearchRepository<OrderEventES, String> {
}
