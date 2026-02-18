package org.example;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UsandoStreams {

    static void main() {

        int sum = IntStream.generate(() -> (int) (Math.random() * 10))
                .takeWhile(n -> n != 3)
                .sum();

        System.out.println("Sum: " + sum + "");

        Stream.of(new Food("Cake"), new Drink("Tea"), new Food("Pizza"))
                .forEach(p -> p.setPrice(10.0));

    }
}
