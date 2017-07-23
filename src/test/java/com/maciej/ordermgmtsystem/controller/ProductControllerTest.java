package com.maciej.ordermgmtsystem.controller;

import com.maciej.ordermgmtsystem.domain.Product;
import com.maciej.ordermgmtsystem.manager.ProductManager;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Test
    public void products() {
        ProductManager manager = mock(ProductManager.class);
        Product expected = new Product("isin", "desc");
        when(manager.getAll()).thenReturn(Lists.newArrayList(expected));

        Collection<Product> result = new ProductController(manager).products();

        assertEquals("size", 1, result.size());
        Product actual = result.iterator().next();
        assertEquals("isin", expected.getIsin(), actual.getIsin());
        assertEquals("description", expected.getDescription(), actual.getDescription());
    }

}