package org.example.interfacesfuncionales.intermedias;

import java.util.stream.Stream;

public class MasIntermedios {

    static void main() {
        Stream.of("A", "C", "B", "D", "B", "D")
                .distinct()
                .sorted()
                .skip(2)
                .map(String::toLowerCase)
                .forEach(System.out::print);

        System.out.println();

        Stream.of("B", "C", "A", "E", "D", "F")
                .takeWhile(s -> !s.equals("D"))
                .dropWhile(s -> !s.equals("C"))
                .limit(2)
                .map(s -> s.toLowerCase())
                .forEach(System.out::print);
    }
}
