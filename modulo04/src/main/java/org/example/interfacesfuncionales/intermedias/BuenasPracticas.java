package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Drink;
import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BuenasPracticas {

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

        //correcto - cada descuento es independiente
        products.stream().parallel()
                .mapToDouble(p -> p.getDiscount())  // Solo lee SU propio descuento
                .sum();

        //incorrecto  - un elemento modifica estado compartido
//        double total = 0;
//        products.stream().parallel()
//                .forEach(p -> total += p.getPrice());  // Varios hilos modifican "total" a la vez


        // SIN INTERFERENCIA - no modifiques  la lista mientras el stream la está procesando
        // PELIGROSO
        products.stream().parallel()
                .forEach(p -> {
                    if (p.getPrice() > 10) {
                        //products.remove(p);    // ¡Estás modificando la lista mientras la recorres!
                    }
                });

        //correcto  - crear una nueva lista con el resultado
        List<Product> filtered = products.stream().parallel()
                .filter(p -> p.getPrice() <= 10)
                .collect(Collectors.toList());


        // ASOCIATIVA
        // El resultado no debe depender del orden en que se procesen los elementos
        // (3 + 5) + 2 = 10
        // 3 + (5 + 2) = 10
        products.stream().parallel()
                .mapToDouble(p -> p.getDiscount())
                .sum();

        // (10 - 3) - 2 = 5
        // 10 - (3 - 2) = 9   ← resultado diferente!

        //Cuando usas PARALLEL
        //Lista: [Café, Leche, Pan, Queso, Yogurt, Jugo]

        /*
            CPU Core 1: procesa [Café, Leche]        → resultado parcial A
            CPU Core 2: procesa [Pan, Queso]          → resultado parcial B
            CPU Core 3: procesa [Yogurt, Jugo]        → resultado parcial C
            Resultado final = combinar(A, B, C)
         */


// llenar la lista ANTES de procesar
        Double discount = products.stream().parallel()
                .mapToDouble(p -> p.getDiscount())  // cada descuento es independiente ✅
                .sum() ;
        System.out.println("Descuento total: " + discount);

    }
}
