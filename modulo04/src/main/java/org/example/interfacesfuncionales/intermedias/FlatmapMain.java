package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FlatmapMain {

    static void main() {
        List<MyOrder> orders = new ArrayList<>();

// Crear productos por orden
        orders.add(new MyOrder(List.of(
                new Product("Tea", 2.50),
                new Product("Cake", 2.99)
        )));
        orders.add(new MyOrder(List.of(
                new Product("Tea", 2.50),
                new Product("Bread", 1.80)
        )));

        //cuanto gaste en Tea en todas las ordenes.
        double x = orders.stream()
                .flatMap(MyOrder::items)
                .filter(p -> p.getName().equals("Tea"))
                .mapToDouble(Product::getPrice)
                .sum();

        System.out.println("Total de Tea en todas las ordenes: " + x);

    }
}


class MyOrder {
    private List<Product> items;

    public MyOrder(List<Product> items) {
        this.items = new ArrayList<>(items);
    }

    public Stream<Product> items() {
        return items.stream();
    }
}