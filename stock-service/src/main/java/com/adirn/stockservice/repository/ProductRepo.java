package com.adirn.stockservice.repository;

import com.adirn.basemodels.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product, Integer> {
}
