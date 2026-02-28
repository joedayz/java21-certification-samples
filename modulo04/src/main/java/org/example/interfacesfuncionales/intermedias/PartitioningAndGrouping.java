package org.example.interfacesfuncionales.intermedias;

import com.sun.security.jgss.GSSUtil;
import org.example.interfacesfuncionales.Drink;
import org.example.interfacesfuncionales.Product;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PartitioningAndGrouping {

    static void main() {
        List<Product> products = List.of(
                new Product("Café Molido", 12.50, 0.10, LocalDate.of(2026, 6, 15)),
                new Drink("Leche Entera", 4.20, 0.00, LocalDate.of(2026, 3, 10)),
                new Product("Pan Integral", 3.80, 0.15, LocalDate.of(2026, 2, 28)),
                new Product("Queso Gouda", 18.90, 0.05, LocalDate.of(2026, 9, 18)),
                new Drink("Yogurt Natural", 5.50, 0.20, LocalDate.of(2026, 3, 5)),
                new Drink("Jugo de Naranja", 6.00, 0.00, LocalDate.of(2026, 5, 12)),
                new Product("Galletas Avena", 8.30, 0.10, LocalDate.of(2026, 8, 30)),
                new Product("Mantequilla", 9.70, 0.00, LocalDate.of(2026, 9, 18)),
                new Product("Cereal Integral", 14.00, 0.25, LocalDate.of(2026, 7, 22)),
                new Product("Mermelada Fresa", 7.60, 0.05, LocalDate.of(2026, 9, 18))
        );

        // Particionar: true = Drink, false = otros productos
        Map<Boolean, List<Product>> productTypes =
                products.stream()
                        .collect(Collectors.partitioningBy(p -> p instanceof Drink));

        System.out.println("Particionado por tipo de producto: " + productTypes + "");


        // Agrupar productos por fecha de vencimiento
        Map<LocalDate, List<Product>> productGroups =
                products.stream()
                        .collect(Collectors.groupingBy(p -> p.getBestBefore()));
        System.out.println("Productos por fecha de vencimiento: " + productGroups + "");

    }
}
