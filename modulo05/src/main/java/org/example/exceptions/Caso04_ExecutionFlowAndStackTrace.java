package org.example.exceptions;

/**
 * CASO 4: Excepciones y el flujo de ejecución.
 *
 * Cuando ocurre una runtime exception sin handler:
 * - El flujo se interrumpe desde la sentencia actual
 * - El control pasa al handler más cercano, o si no hay, el programa termina
 * - Se imprime el stack trace mostrando el camino de propagación
 *
 * Ejecutar SIN try-catch para ver el stack trace completo.
 */
public class Caso04_ExecutionFlowAndStackTrace {

    public static void main(String[] args) {
        System.out.println("Inicio main");
        int x = 5;
        int y = 0;
        int z = divide(x, y);  // <- excepción propaga desde aquí
        System.out.println(z); // <- NUNCA se ejecuta
        System.out.println("Fin main");
    }

    public static int divide(int x, int y) {
        int z = x / y;  // <- ArithmeticException: / by zero
        return z;       // <- NUNCA se ejecuta
    }
}
