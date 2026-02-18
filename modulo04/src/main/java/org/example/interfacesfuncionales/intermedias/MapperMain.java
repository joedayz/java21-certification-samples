package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;

import static org.example.interfacesfuncionales.intermedias.Main.crearProducto;

public class MapperMain {

    static void main() {

        List<Product> list = new ArrayList<>();
        list.add(crearProducto("Leche", "9.50", LocalDate.now().plusDays(7)));
        list.add(crearProducto("Pan", "3.20", LocalDate.now().plusDays(2)));
        list.add(crearProducto("Café", "12.00", LocalDate.now().plusDays(90)));
        list.add(crearProducto("Yogur", "2.80", LocalDate.now().plusDays(5)));
        list.add(crearProducto("Queso", "15.99", LocalDate.now().plusDays(30)));

        Function<Product, String> nameMapper = p -> p.getName();
        UnaryOperator<String> trimMapper = n -> n.trim();
        ToIntFunction<String> lengthMapper = n -> n.length();

        int total = list.stream()
                .map(nameMapper.andThen(trimMapper))
                .mapToInt(lengthMapper)
                .sum();

        System.out.println("Total de caracteres de todos los productos es: " + total);

    }
}
