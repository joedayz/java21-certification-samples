package org.example.interfacesfuncionales.bi;

import java.util.List;
import java.util.function.BiPredicate;

public class BiPredicateMain {

    static void main() {
        BiPredicate<String, Integer> mismaLongitud = (s,n) -> s.length() == n;

        boolean r = mismaLongitud.test("Hola", 4); //true

        System.out.println("Resultado: " + r + "");

        BiPredicate<List<?>, Integer> tieneTamano = (list, size) -> list.size() >= size;

        r = tieneTamano.test(List.of(1, 2, 3), 2);

        System.out.println("Resultado: " + r + "");

    }
}
