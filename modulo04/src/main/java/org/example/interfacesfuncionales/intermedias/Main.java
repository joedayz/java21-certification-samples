package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Main {

    static void main() {

        //data

        // Lista llena de productos
        List<Product> list = new ArrayList<>();
        list.add(crearProducto("Leche", "9.50", LocalDate.now().plusDays(7)));
        list.add(crearProducto("Pan", "3.20", LocalDate.now().plusDays(2)));
        list.add(crearProducto("Café", "12.00", LocalDate.now().plusDays(90)));
        list.add(crearProducto("Yogur", "2.80", LocalDate.now().plusDays(5)));
        list.add(crearProducto("Queso", "15.99", LocalDate.now().plusDays(30)));

        //Consumers

        Consumer<Product> expireProduct = (p) -> p.setBestBefore(LocalDate.now());
        Consumer<Product> discountProduct = (p) -> p.setDiscount(0.1);

        //A cada producto: primero expirar y luego aplicar descuento
        list.forEach(expireProduct.andThen(discountProduct));

        //a todos: expirar (peek), solo a los de precio > 10: aplicar descuento
        list.stream()
                .peek(expireProduct)
                .filter(p -> p.getPrice() > 10)
                .forEach(discountProduct);



    }

    static Product crearProducto(String nombre, String precio, LocalDate bestBefore) {
        Product p = new Product(nombre);
        p.setPrice(Double.parseDouble(precio));
        p.setBestBefore(bestBefore);
        return p;
    }
}
