package pe.joedayz.comparator;

import java.util.Arrays;

public class Product {

    private String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
        pe.joedayz.comparator.Product[] products = {
                new pe.joedayz.comparator.Product("Tea")      ,
                new pe.joedayz.comparator.Product("Coffee"),
                new pe.joedayz.comparator.Product("Milk")
        };
        Arrays.sort(products, new ProductNameSorter());

        Arrays.stream(products).forEach(System.out::println);
    }
}
