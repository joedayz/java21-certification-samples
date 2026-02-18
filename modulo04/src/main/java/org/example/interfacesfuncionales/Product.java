package org.example.interfacesfuncionales;

import java.time.LocalDate;

public class Product {

    String name;
    double price;
    private double discount = 0;      // 0 = sin descuento
    private LocalDate bestBefore;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }

    public void applyDiscount(double factor) {
        this.discount = factor;  // o la lógica que quieras (ej. price *= (1 - factor))
    }
}
