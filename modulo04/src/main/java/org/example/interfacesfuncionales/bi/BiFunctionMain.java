package org.example.interfacesfuncionales.bi;

import java.util.function.BiFunction;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToIntBiFunction;

public class BiFunctionMain {

    static void main() {
        BiFunction<String, String, String> concat  = (a, b) -> a + " " + b;

        String r = concat.apply("Hola", "Mundo");

        System.out.println(r);

        BiFunction<Integer, Integer, Integer> suma = (a, b) -> a + b;
        System.out.println("Suma es " + suma.apply(10, 5));

        //Variantes primitivas
        ToIntBiFunction<String, String> totalChars = (a, b) -> a.length() + b.length();

        int n = totalChars.applyAsInt("abc", "xy"); //5
        System.out.println("Total de caracteres: " + n);

        ToDoubleBiFunction<Integer, Integer> promedio = (a, b) -> (a + b) / 2.0;
        double p = promedio.applyAsDouble(10, 20);  // 15.0
        System.out.println("Promedio: " + p);
    }
}
