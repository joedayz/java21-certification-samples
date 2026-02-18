package org.example.interfacesfuncionales.bi;

import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;
import java.util.function.LongBinaryOperator;

public class BinaryOperatorMain {

    static void main() {
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("Max = " + max.apply(10, 20));

        BinaryOperator<String> mayor = (a, b) -> a.compareTo(b) > 0 ? a : b;
        System.out.println("Mayor es = " + mayor.apply("z", "a"));

        IntBinaryOperator suma = (a, b) -> a + b;
        int s = suma.applyAsInt(10, 5);  // 15

        LongBinaryOperator producto = (a, b) -> a * b;
        long p = producto.applyAsLong(100L, 3L);  // 300
    }
}
