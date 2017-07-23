package com.maciej.ordermgmtsystem.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Product {
    private final String isin;
    private final String description;

    // Production system likely to use internal ID in addition to ISIN
    @JsonCreator
    public Product(@JsonProperty("isin") String isin, @JsonProperty("description") String description) {
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

