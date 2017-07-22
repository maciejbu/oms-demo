package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.dao.ProductDao;
import com.maciej.ordermgmtsystem.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @GetMapping
    public Collection<Product> products() {
        return productDao.getAll();
    }

    @GetMapping("/{isin}")
    public Product product(@PathVariable("isin") String isin) {
        return productDao.get(isin);
    }

}
