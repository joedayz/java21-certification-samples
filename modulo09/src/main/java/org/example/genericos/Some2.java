package org.example.genericos;

import java.util.function.UnaryOperator;

public class Some2 implements UnaryOperator<String> {
    public String apply(String s) {
        return s.toUpperCase();
    }

    public static void main(String[] args) {
        Some2 some2 = new Some2();
        String result = some2.apply("Hello, Generics!");
        System.out.println(result);
    }
}
