package org.example.genericos.willcards;

import org.example.genericos.Drink;
import org.example.genericos.Food;
import org.example.genericos.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List listOfAnyObjects1 = new ArrayList<>();

        List<Object> listOfAnyObjects2 = new ArrayList<>();

        List<Product> listOfProducts = new ArrayList<>();

        List<?> listOfAnyObjects3 = new ArrayList<>();

        listOfAnyObjects3.add(null);


        Product p1 = new Food();
        Product p2 = new Drink();
        Product p3 = new Food();

        List<Product> products = List.of(p1, p2, p3);
        List<Food> foods = List.of((Food) p1, (Food) p3);
        List<Object> objects = new ArrayList<>();


        setProducts(products);

        //upper wildcard

        setProductAndSubtypes(products);
        setProductAndSubtypes(foods);

        // lower wildcard
        Food f = new Food();
        addFoodToFoods(foods, f);


        //addFoodToFoods(products, f);

        addFoodToFoodParents(foods, f);

        addFoodToFoodParents(products, f);

        addFoodToFoodParents(objects, f);
    }


    // Solo acepta exactamente List<Product>
    public static void setProducts(List<Product> products) {
        // Se puede escribir (add)
        products.add(new Product());
    }

    public static void setProductAndSubtypes(List<? extends Product> products) {
        // No se puede hacer add (excepto null)
        // products.add(new Product("Test", 1.0)); ❌ ERROR

        // Pero sí se puede leer
        for (Product p : products) {
            System.out.println(p);
        }
    }


    // Solo acepta exactamente List<Food>
    public static void addFoodToFoods(List<Food> order, Food food) {
        order.add(food);
    }

    // List<? super Food> → contravariante → acepta List<Food>, List<Product>, List<Object>
    public static void addFoodToFoodParents(List<? super Food> order, Food food) {
        order.add(food);
    }

}
