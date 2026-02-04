package pe.joedayz.comparable;

import java.util.Arrays;

public class Product implements Comparable<Product>{
    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Product[] products = {
          new Product("Tea")      ,
                new Product("Coffee"),
                new Product("Milk")
        };

        Arrays.sort(products);

        Arrays.stream(products).forEach(System.out::println);
    }
}
