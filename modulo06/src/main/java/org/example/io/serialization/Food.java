package org.example.io.serialization;

import java.math.BigDecimal;

public class Food extends Product {

    private static final long serialVersionUID = 1L;

    public Food() {
    }

    public Food(String name, double price) {
        super(name, BigDecimal.valueOf(price));
    }
}
