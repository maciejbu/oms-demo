package com.maciej.ordermgmtsystem.dao;

import com.maciej.ordermgmtsystem.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class FixedProductsDao implements ProductDao {

    private final Map<String, Product> productsMap = new HashMap<>();

    public FixedProductsDao() {
        add(new Product( "FR0000131104", "BNP Paribas SA"));
        add(new Product( "CH0244767585", "UBS Group AG (USA)"));
    }

    private void add(Product product) {
        productsMap.put(product.getIsin(), product);
    }

    @Override
    public Collection<Product> getAll() {
        return productsMap.values();
    }

    @Override
    public Product get(String isin) {
        return productsMap.get(isin);
    }


}
