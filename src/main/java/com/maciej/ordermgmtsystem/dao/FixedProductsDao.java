package com.maciej.ordermgmtsystem.dao;

import com.maciej.ordermgmtsystem.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FixedProductsDao implements ProductDao {

    private final Map<Long, Product> productsMap = new HashMap<>();

    public FixedProductsDao() {
        add(new Product(1, "EPA:BNP", "BNP Paribas SA"));
        add(new Product(2, "NYSE:UBS", "UBS Group AG (USA)"));
    }

    private void add(Product product) {
        productsMap.put(product.getId(), product);
    }

    @Override
    public Collection<Product> getAll() {
        return productsMap.values();
    }

    @Override
    public Product get(long id) {
        return productsMap.get(id);
    }


}
