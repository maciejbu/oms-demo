package com.maciej.ordermgmtsystem.dao;

import com.maciej.ordermgmtsystem.domain.Product;

import java.util.Collection;


public interface ProductDao {

    Collection<Product> getAll();

    Product get(String isin);
}
