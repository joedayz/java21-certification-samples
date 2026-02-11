package org.example.coleccionesconcurrencia;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class UnmodifiableMain {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("Tea");
        set.add("Cake");
        set.add("Coffee");
        set.add("Cookie");

        Set<String> readOnlySet = Collections.unmodifiableSet(set);

        System.out.println(readOnlySet); // [Tea, Cake, Coffee, Cookie]

        // Esto LANZA UnsupportedOperationException:
        // readOnlySet.add("Pie");      // ERROR!
        // readOnlySet.remove("Tea");   // ERROR!

        set.add("Pie"); // Esto funciona
        System.out.println(readOnlySet);

        // List.of (..), Set.of (..), Map.of(..)
    }
}
