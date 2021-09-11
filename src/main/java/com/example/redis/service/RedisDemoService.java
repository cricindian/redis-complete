package com.example.redis.service;

import com.example.redis.dataLayer.DataLayer;
import com.example.redis.exception.NotFoundException;
import com.example.redis.model.Item;
import com.example.redis.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RedisDemoService {

    @Autowired
    private DataLayer dataLayer;

    public Product saveProduct(Product product) {
        return dataLayer.saveProduct(product);
    }

    public Product updateProduct(Product product) {
        return dataLayer.saveProduct(product);
    }


    public Product getProductById(Integer id){
        Product product = dataLayer.getProductById(id);
        if(product==null)
            throw new NotFoundException("Product with id :"+id+" not found");
        return product;
    }

    public List<Item> getItemsByProductId(Integer productId) {
        return getProductById(productId).getItems();
    }

    public Item getItemByProductIdAndItemId(Integer productId, Integer itemId) {
        List<Item> items =  getProductById(productId).getItems();
        Optional<Item> optionalItem =items.stream().filter(item -> (item.getId()-itemId) == 0).findFirst();
        if(!optionalItem.isPresent())
            throw new NotFoundException("Item with id :"+itemId+" not found in product "+productId);
        return optionalItem.get();
    }
}
