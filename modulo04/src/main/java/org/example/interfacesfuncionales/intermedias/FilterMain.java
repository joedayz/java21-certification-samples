package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.example.interfacesfuncionales.intermedias.Main.crearProducto;

public class FilterMain {

    static void main() {

        List<Product> list = new ArrayList<>();
        list.add(crearProducto("Leche", "9.50", LocalDate.now().plusDays(7)));
        list.add(crearProducto("Pan", "3.20", LocalDate.now().plusDays(2)));
        list.add(crearProducto("Café", "12.00", LocalDate.now().plusDays(90)));
        list.add(crearProducto("Yogur", "2.80", LocalDate.now().plusDays(5)));
        list.add(crearProducto("Queso", "15.99", LocalDate.now().plusDays(30)));

        // Predicados
        Predicate<Product> foodFilter = p -> p instanceof Food;
        Predicate<Product> priceFilter = p -> p.getPrice() < 2;

        //descuento a NO Food o precio<2
        list.stream()
                .filter(foodFilter.negate().or(priceFilter))
                .forEach(p -> p.setDiscount(0.1));

        //descuento solo al producto igual a Cake, 1.99
        list.stream()
                .filter(Predicate.isEqual(new Food("Cake", 1.99)))
                .forEach(p -> p.setDiscount(0.2));



    }
}
