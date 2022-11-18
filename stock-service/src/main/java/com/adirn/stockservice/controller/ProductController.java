package com.adirn.stockservice.controller;

import com.adirn.basemodels.models.Product;
import com.adirn.stockservice.repository.ProductRepo;
import com.adirn.stockservice.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductRepo repo;

    @Autowired
    ProductService productService;

    @PostMapping("/products")
    public ResponseEntity<?> newProduct(@RequestBody Product product){
        repo.save(product);
        return new ResponseEntity<>("Product created ....", HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<?> getAll(){
        List<Product> products = repo.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getById(@PathVariable int id){
        return new ResponseEntity<Product>(productService.getbyid(id), HttpStatus.OK);
    }
}
