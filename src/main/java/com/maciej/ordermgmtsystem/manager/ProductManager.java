package com.maciej.ordermgmtsystem.manager;

import com.maciej.ordermgmtsystem.domain.Product;

import java.util.Collection;

public interface ProductManager {
    Collection<Product> getAll();
}
