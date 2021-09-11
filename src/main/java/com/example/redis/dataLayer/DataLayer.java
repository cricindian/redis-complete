package com.example.redis.dataLayer;

import com.example.redis.model.Item;
import com.example.redis.model.Product;
import com.example.redis.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataLayer {

    private static final String PRODUCT_CACHE_KEY = "PRODUCT_CACHE_KEY";
    private static final String ITEM_CACHE_KEY = "ITEM_CACHE_KEY";

    @Autowired
    private RedisUtil<Product> productCache;

    @Autowired
    private RedisUtil<Item> itemCache;

    public Product saveProduct(Product product) {
        productCache.putMap(PRODUCT_CACHE_KEY, product.getId(), product);
//        saveItems(product); // if need to store item separately
        return product;
    }

    private void saveItems(Product product) {
        product.getItems().stream().forEach(this::saveItem);
    }

    // If there is a need to save item separately
    public Item saveItem(Item item){
        itemCache.putMap(ITEM_CACHE_KEY, item.getId(), item);
        return item;
    }

    public Product getProductById(Integer id) {
        return productCache.getMapAsSingleEntry(PRODUCT_CACHE_KEY, id);
    }

    public Item getItemById(Integer id) {
        return itemCache.getMapAsSingleEntry(ITEM_CACHE_KEY, id);
    }
}
