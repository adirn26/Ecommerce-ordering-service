package com.adirn.stockservice.controller;

import com.adirn.basemodels.models.Product;
import com.adirn.stockservice.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepo repo;

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
        Product product = repo.findById(id).get();
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
}
