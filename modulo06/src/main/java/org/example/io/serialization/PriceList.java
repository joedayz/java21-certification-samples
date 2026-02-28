package org.example.io.serialization;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PriceList implements Serializable {

    private static final long serialVersionUID = 1L;

    private LocalDate date;
    private Set<Product> items = new HashSet<>();
    private transient String hash;  // No se serializa - solo en memoria

    public PriceList(LocalDate date) {
        this.date = date;
        this.hash = generateHash();
    }

    public void addItem(Product p) {
        items.add(p);
    }

    public LocalDate getDate() {
        return date;
    }

    public Set<Product> getItems() {
        return items;
    }

    public String getHash() {
        return hash;
    }

    private String generateHash() {
        return "hash-" + date + "-" + System.nanoTime();
    }

    @Override
    public String toString() {
        return "PriceList{date=" + date + ", items=" + items.size() + ", hash=" + hash + "}";
    }
}
