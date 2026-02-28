package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Drink;
import org.example.interfacesfuncionales.Product;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CollectMain {

    static void main() {

        /*

        1. SUPPLIER (Proveedor)     → Crea la caja vacía donde guardar resultados
2. ACCUMULATOR (Acumulador) → Mete cada elemento en la caja
3. COMBINER (Combinador)    → Junta varias cajas en una (para parallel)
4. FINISHER (Finalizador)   → Transformación final opcional



           Elementos: [A, B, C, D, E, F]

          En paralelo:

            Hilo 1:   [A, B, C] → Supplier crea caja₁ → Accumulator mete A,B,C → [caja₁]
                                                                           ↘
            Hilo 2:   [D, E, F] → Supplier crea caja₂ → Accumulator mete D,E,F → [caja₂] → Combiner junta → [caja final] → Finisher transforma → RESULTADO


         */


        List<Product> list = List.of(
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

        List<String> names = list.stream()
                .collect(
                        () -> new ArrayList<>(),             // Supplier: crear lista vacía
                        (acc, p) -> acc.add(p.getName()),    // Accumulator: agregar nombre a la lista
                        (list1, list2) -> list1.addAll(list2) // Combiner: juntar dos listas (para parallel)
                );

        List<String> names2 = list.stream()
                .map(p -> p.getName())
                .collect(Collectors.toList());   // Internamente hace lo mismo que arriba


        String result = list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.averagingDouble(p -> p.getPrice()),  // Collector: calcular promedio
                        avg -> "Precio promedio: $" + String.format("%.2f", avg)  // Finisher: formatear
                ));
    }
}
