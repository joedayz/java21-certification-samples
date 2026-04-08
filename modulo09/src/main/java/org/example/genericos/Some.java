package org.example.genericos;

public class Some <U>{

    public U apply (U u) {
        return u;
    }

    public static void main(String[] args) {
        Some<String> some = new Some<>();
        String result = some.apply("Hello, Generics!");
        System.out.println(result);
    }
}


