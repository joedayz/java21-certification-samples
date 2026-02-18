package org.example;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        List<Product> products = new ArrayList<>();
        products.add(new Product("Product 1"));
        products.add(new Product("Product 2"));
        products.add(new Product("Product 3"));


        products.stream().parallel()
                .filter(product -> product.getName().contains("2"))
                .forEach(System.out::println);

    }
}
