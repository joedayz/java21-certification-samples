package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OperationReduceMain {

    static void main() {

        List<Product> products = List.of(
                new Product("Café Molido",      12.50, 0.10, LocalDate.of(2026, 6, 15)),
                new Product("Leche Entera",      4.20, 0.00, LocalDate.of(2026, 3, 10)),
                new Product("Pan Integral",      3.80, 0.15, LocalDate.of(2026, 2, 28)),
                new Product("Queso Gouda",      18.90, 0.05, LocalDate.of(2026, 4, 20)),
                new Product("Yogurt Natural",    5.50, 0.20, LocalDate.of(2026, 3, 5)),
                new Product("Jugo de Naranja",   6.00, 0.00, LocalDate.of(2026, 5, 12)),
                new Product("Galletas Avena",    8.30, 0.10, LocalDate.of(2026, 8, 30)),
                new Product("Mantequilla",       9.70, 0.00, LocalDate.of(2026, 4, 1)),
                new Product("Cereal Integral",  14.00, 0.25, LocalDate.of(2026, 7, 22)),
                new Product("Mermelada Fresa",   7.60, 0.05, LocalDate.of(2026, 9, 18))
        );

        //simple reduction
        Optional<String> x1 = products.stream()
                .map(p -> p.getName())
                .reduce((s1, s2) -> s1 + " " + s2);
        System.out.println(x1.orElse("no data"));


        // Reduction with initial (default) value
        String x2 = products.stream()
                .map(p -> p.getName())
                .reduce("", (s1, s2) -> s1 + " " + s2);

        System.out.println(x2);

        // Reduction with initial (default) value and a parallel combiner
        String x3 = products.stream()
                .parallel()
                .reduce("", (s, p) -> p.getName() + " " + s, (s1, s2) -> s1 + s2);
        System.out.println(x3);
    }
}
