package com.adirn.stockservice.service;

import com.adirn.basemodels.models.Product;
import com.adirn.stockservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;

    @Cacheable(value = Product.HASH_VALUE, key="#id")
    public Product getbyid(int id){
        return repo.findById(id).get();
    }
}
