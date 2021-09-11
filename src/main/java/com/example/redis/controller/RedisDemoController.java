package com.example.redis.controller;

import com.example.redis.model.Item;
import com.example.redis.model.Product;
import com.example.redis.service.RedisDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
public class RedisDemoController {

    @Autowired
    RedisDemoService service;

    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product){
        Product p =  service.saveProduct(product);
        return new ResponseEntity<>(p, HttpStatus.ACCEPTED);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer id){
        Product p =  service.getProductById(id);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/items")
    public ResponseEntity<List<Item>> getItemsByProductId(@PathVariable Integer productId){
        List<Item> items =  service.getItemsByProductId(productId);
        return new ResponseEntity<>(items, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}/items/{itemId}")
    public ResponseEntity<Item> getItemsByProductId(@PathVariable Integer productId, @PathVariable Integer itemId){
        Item item =  service.getItemByProductIdAndItemId(productId, itemId);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
}
