package org.example.interfacesfuncionales;

import java.util.stream.Stream;

public class VariantesPrimitivas {

    static void main() {
        int sum = Stream.of("ONE", "TWO", "THREE", "FOUR")
                .mapToInt(s -> s.length())  // ToIntFunction<String> → IntStream
                .peek( i -> System.out.println(i)) //IntConsumer
                .filter( i -> i> 3) //IntPredicate
                .sum();

        System.out.println("Sum = " + sum + "");
    }
}
