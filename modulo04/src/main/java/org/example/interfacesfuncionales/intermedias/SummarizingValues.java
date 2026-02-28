package org.example.interfacesfuncionales.intermedias;


import org.example.interfacesfuncionales.Drink;
import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class SummarizingValues {

    static void main() {

        List<Product> products = List.of(
                new Product("Café Molido",      12.50, 0.10, LocalDate.of(2026, 6, 15)),
                new Drink("Leche Entera",      4.20, 0.00, LocalDate.of(2026, 3, 10)),
                new Product("Pan Integral",      3.80, 0.15, LocalDate.of(2026, 2, 28)),
                new Product("Queso Gouda",      18.90, 0.05, LocalDate.of(2026, 4, 20)),
                new Drink("Yogurt Natural",    5.50, 0.20, LocalDate.of(2026, 3, 5)),
                new Drink("Jugo de Naranja",   6.00, 0.00, LocalDate.of(2026, 5, 12)),
                new Product("Galletas Avena",    8.30, 0.10, LocalDate.of(2026, 8, 30)),
                new Product("Mantequilla",       9.70, 0.00, LocalDate.of(2026, 4, 1)),
                new Product("Cereal Integral",  14.00, 0.25, LocalDate.of(2026, 7, 22)),
                new Product("Mermelada Fresa",   7.60, 0.05, LocalDate.of(2026, 9, 18))
        );


        // Estadísticas resumidas de precios (count, sum, min, average, max)
        DoubleSummaryStatistics stats =
                products.stream()
                        .collect(Collectors.summarizingDouble(p -> p.getPrice()));
        System.out.println("Stats: " + stats + "");


        // Concatenar nombres separados por coma
        String s1 =
                products.stream()
                        .collect(Collectors.mapping(p -> p.getName(), Collectors.joining(",")));
        System.out.println("s1: " + s1 + "");

        // Filtrar solo productos que sean instancia de Drink
        List<Product> drinks =
                products.stream()
                        .filter(p -> p instanceof Drink)
                        .collect(Collectors.toList());
        System.out.println("drinks: " + drinks + "");
    }
}
