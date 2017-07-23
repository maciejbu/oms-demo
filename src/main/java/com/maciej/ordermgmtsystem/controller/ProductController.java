package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.dao.ProductDao;
import com.maciej.ordermgmtsystem.domain.Product;
import com.maciej.ordermgmtsystem.manager.ProductManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/rest/products")
public class ProductController {
    private final ProductManager productManager;

    public ProductController(ProductManager productManager) {
        this.productManager = productManager;
    }

    @GetMapping
    public Collection<Product> products() {
        return productManager.getAll();
    }

/*    @GetMapping("/{isin}")
    public Product product(@PathVariable("isin") String isin) {
        return productDao.get(isin);
    }*/

}
