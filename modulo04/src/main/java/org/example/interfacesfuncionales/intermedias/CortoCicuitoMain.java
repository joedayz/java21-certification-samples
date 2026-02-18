package org.example.interfacesfuncionales.intermedias;

import java.util.Arrays;
import java.util.Optional;

public class CortoCicuitoMain {

    static void main() {

        String[] values = {"RED", "GREEN", "BLUE"};

        boolean allGreen = Arrays.stream(values).allMatch(s -> s.equals("GREEN"));
        System.out.println(allGreen);
        boolean anyGreen = Arrays.stream(values).anyMatch(s -> s.equals("GREEN"));
        System.out.println(anyGreen);
        boolean noneGreen = Arrays.stream(values).noneMatch(s -> s.equals("GREEN"));
        System.out.println(noneGreen);

        Optional<String> anyColour = Arrays.stream(values).findAny();
        Optional<String> firstColour = Arrays.stream(values).findFirst();

        System.out.println(anyColour);
        System.out.println(firstColour);
    }
}
