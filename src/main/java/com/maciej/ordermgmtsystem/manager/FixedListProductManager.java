package com.maciej.ordermgmtsystem.manager;

import com.maciej.ordermgmtsystem.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class FixedListProductManager implements ProductManager {
    private final Map<String, Product> productsMap = new HashMap<>();

    public FixedListProductManager() {
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
}
