package org.example.collectionssample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Product implements Comparable<Product>{

    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Food extends Product {
    public Food(String name) {
        super(name);
    }
}

class Drink extends Product {
    public Drink(String name) {
        super(name);
    }
}


class CollectionsDemo{
    public static void main(String[] args) {
        Product p1 = new Food("Cake");
        Product p2 = new Drink("Tea");
        Product p3 = new Food("Cookie");

        List<Product> menu = new ArrayList<>();
        menu.add(p1);
        menu.add(p2);
        menu.add(p3);

        System.out.println("Lista original:");
        System.out.println(menu);

        Collections.sort(menu);
        System.out.println("Lista ordenada:");
        System.out.println(menu);


        Collections.reverse(menu);
        System.out.println("Lista invertida:");
        System.out.println(menu);


        Collections.shuffle(menu);
        System.out.println("Lista aleatoria:");
        System.out.println(menu);


        Collections.sort(menu);
        int index = Collections.binarySearch(menu, p2);
        System.out.println("Índice de 'Tea': " + index);  // 2

        Collections.fill(menu, new Food("Pie"));
        System.out.println("\n=== Después de Collections.fill(menu, new Food(\"Pie\")) ===");
        System.out.println(menu);  // [Pie, Pie, Pie]

    }
}







