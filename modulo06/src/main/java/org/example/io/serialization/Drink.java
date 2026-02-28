package org.example.io.serialization;

import java.math.BigDecimal;

public class Drink extends Product {

    private static final long serialVersionUID = 1L;

    public Drink() {
    }

    public Drink(String name, double price) {
        super(name, BigDecimal.valueOf(price));
    }
}
