package org.example.interfacesfuncionales;

import java.time.LocalDate;

public class Drink extends Product{
    public Drink(String name) {
        super(name);
    }

    public Drink(String yogurtNatural, double v, double v1, LocalDate of) {
        super(yogurtNatural, v, v1, of);
    }

    @Override
    public String toString() {
        return "Drink{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
