package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * CASO 2: Throwing exceptions - checked vs unchecked.
 *
 * Para lanzar una excepción:
 * 1. Crear instancia: new IOException(), new NullPointerException()
 * 2. Usar operador throw: throw new IOException();
 *
 * Si no hay handler en el método:
 * - Unchecked: se propaga automáticamente al invocador
 * - Checked: debe declararse en la cláusula throws
 */
public class Caso02_ThrowingExceptions {

    public static void main(String[] args) {
        System.out.println("=== Caso 2a: Lanzar IOException (checked) ===\n");
        try {
            doThings("io");
        } catch (IOException | ProductException e) {
            System.out.println("Capturado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        System.out.println("\n=== Caso 2b: Lanzar ProductException (checked) ===\n");
        try {
            doThings("custom");
        } catch (IOException | ProductException e) {
            System.out.println("Capturado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        System.out.println("\n=== Caso 2c: Lanzar NullPointerException (unchecked - NO en throws) ===\n");
        try {
            doThings("npe");
        } catch (IOException | ProductException e) {
            System.out.println("No llega aquí para NPE");
        }
    }

    // IOException y ProductException son checked -> deben estar en throws
    // NullPointerException es unchecked -> NO debe declararse
    public static void doThings(String tipo) throws IOException, ProductException {
        switch (tipo) {
            case "io" -> throw new IOException("Error de I/O");
            case "custom" -> throw new ProductException("Error de producto");
            case "npe" -> throw new NullPointerException("Objeto nulo");
            default -> { /* ok */ }
        }
    }
}
