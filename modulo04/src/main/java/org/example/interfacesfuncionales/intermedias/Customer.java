package org.example.interfacesfuncionales.intermedias;

public class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    @Override
    public String toString() { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        return name.equals(((Customer) o).name);
    }

    @Override
    public int hashCode() { return name.hashCode(); }

}
