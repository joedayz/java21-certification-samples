package org.example.interfacesfuncionales.intermedias;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SpliteratorCharacteristics {
    static void main() {


        // ArrayList: ORDERED, SIZED, SUBSIZED
        // → Java sabe el tamaño, puede dividir en mitades exactas para parallel()
        List<String> list = List.of("A", "B", "C", "D");
        // Split: [A, B] y [C, D]  ← mitades exactas

        // HashSet: DISTINCT, SIZED
        // → Java sabe que no hay duplicados, puede saltar verificaciones
        Set<String> set = Set.of("A", "B", "C");

        // TreeSet: DISTINCT, SORTED, ORDERED, SIZED
        // → Java sabe que ya está ordenado, .sorted() no hace nada extra
        TreeSet<Integer> tree = new TreeSet<>(List.of(3, 1, 2));

    }
}
