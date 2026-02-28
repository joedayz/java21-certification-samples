package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Customer customer;
    private LocalDate date;
    private List<Product> items;

    public Order(Customer customer, LocalDate date, List<Product> items) {
        this.customer = customer;
        this.date = date;
        this.items = items;
    }

    public Customer getCustomer() { return customer; }
    public LocalDate getDate() { return date; }
    public List<Product> getItems() { return items; }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", date=" + date +
                '}';
    }
}
