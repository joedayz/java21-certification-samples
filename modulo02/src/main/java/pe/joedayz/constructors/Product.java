package pe.joedayz.constructors;

import java.math.BigDecimal;

public class Product {

    private String name;
    private BigDecimal price;

    public Product(){}

    public Product(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }


    public static void main(String[] args) {
        Product p = new Product("Tea", BigDecimal.valueOf(1.99));

        Product p2 = new Product();
    }
}
