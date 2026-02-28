package org.example.interfacesfuncionales.intermedias;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalDouble;

public class ProcessStreamMain {

    static void main() {
        String[] values = {"RED", "GREEN", "BLUE"};
        long v1 = Arrays.stream(values).filter(s -> s.indexOf('R') != -1).count();       // 2
        System.out.println("v1="+v1);
        int v2 = Arrays.stream(values).mapToInt(v -> v.length()).sum();                   // 12
        System.out.println("v2="+v2);
        OptionalDouble v3 = Arrays.stream(values).mapToInt(v -> v.length()).average();
        double avgValue = v3.isPresent() ? v3.getAsDouble() : 0;                         // 4
        System.out.println("avgValue="+avgValue);
        Optional<String> v4 = Arrays.stream(values).max((s1, s2) -> s1.compareTo(s2));
        Optional<String> v5 = Arrays.stream(values).min((s1, s2) -> s1.compareTo(s2));
        String maxValue = (v4.isPresent()) ? v4.get() : "no data";                       // RED
        System.out.println("maxValue="+maxValue);
        String minValue = (v5.isPresent()) ? v5.get() : "no data";                       // BLUE
        System.out.println("minValue="+minValue);
    }
}
