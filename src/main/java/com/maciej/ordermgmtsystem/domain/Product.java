package com.maciej.ordermgmtsystem.domain;

public class Product {
    private final String isin;
    private final String description;

    // Production system likely to use internal ID in addition to ISIN
    public Product(String isin, String description) {
        this.isin = isin;
        this.description = description;
    }

    public String getIsin() {
        return isin;
    }

    public String getDescription() {
        return description;
    }
}

