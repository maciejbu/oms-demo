package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.dao.ProductDao;
import com.maciej.ordermgmtsystem.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public Collection<Product> products() {
        return productDao.getAll();
    }

    @GetMapping("/{id}")
    public Product product(@PathVariable("id") long id) {
        return productDao.get(id);
    }

}
