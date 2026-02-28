package org.example.exceptions;

/**
 * CASO 6: Lanzar unchecked exception explícitamente.
 *
 * Para producir la excepción:
 * 1. Crear instancia: new ArithmeticException("Error: 5/0")
 * 2. Usar throw para interrumpir el flujo
 *
 * Notas:
 * - No es obligatorio capturar unchecked exceptions
 * - A menudo indican un bug que debe corregirse, no solo capturarse
 */
public class Caso06_UncheckedArithmeticException {

    public static void main(String[] args) {
        int x = 5;
        int y = 0;
        int z = divide(x, y);  // throw ocurre aquí
        System.out.println(z); // no se ejecuta
    }

    public static int divide(int x, int y) {
        if (y == 0) {
            throw new ArithmeticException("Error: " + x + "/" + y);
        }
        int z = x / y;
        return z;
    }
}
