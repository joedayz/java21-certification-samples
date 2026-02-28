package org.example.interfacesfuncionales.intermedias;

import org.example.interfacesfuncionales.Drink;
import org.example.interfacesfuncionales.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RestriccionesEnProcesamientoParalelo {
    static void main() {

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

        List<BigDecimal> prices = new ArrayList<>();
        list.stream()
                .parallel()
                .peek(p -> System.out.println(p))    // ⚠️ Acceso secuencial a System.out (recurso compartido)
                .map(p -> p.getPrice())
                .forEach(p -> prices.add(BigDecimal.valueOf(p)));         // ⚠️Modifica ArrayList compartido (no es thread-safe)

        //LA VERSION CORRECTA

        List<BigDecimal> prices2 = list.stream()
                .parallel()
                .map(p -> BigDecimal.valueOf(p.getPrice()))
                .collect(Collectors.toList()); //Collectors maneja la concurrencia internamente

        // correcto con toConcurrentMap

        Map<String, BigDecimal> namesAndPrices =
                list.stream()
                        .parallel()
                        .collect(Collectors.toConcurrentMap(   // ✅ ConcurrentMap es thread-safe
                                p -> p.getName(),
                                p -> BigDecimal.valueOf(p.getPrice())));

    }

}
