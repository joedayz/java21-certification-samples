package pe.joedayz.reusandoconstructors;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;

    public Product(String name, double price) {
        //Product(name); esto no existe en java para llamar a un constructor
        //new Product(name); si compila pero es crear otro objeto
        this(name);
        this.price = BigDecimal.valueOf(price);
    }

    public Product(String name) {
        this.name = name;
        this.price = BigDecimal.ZERO;
    }

    public static void main(String[] args) {
        new Product("Tea");
        new Product("Coffee", 1.99);
        //new Product(); NO COMPILA
    }
}
