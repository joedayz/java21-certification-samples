package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * CASO 12: Program Flow Producing a Runtime Exception (NullPointerException)
 *
 * Cuando se produce NullPointerException en a():
 * - Flujo: a(), e() (finally)
 * - b(), c(), d(), f() NO se ejecutan
 * - No hay catch que maneje NPE
 * - finally se ejecuta antes de propagar
 * - El programa termina (o propaga) - f() nunca se alcanza
 */
public class Caso12_FlowRuntimeException {

    private static final List<String> executed = new ArrayList<>();

    public static void main(String[] args) {
        try {
            doThings();
        } catch (NullPointerException e) {
            executed.add("[NPE-caught]");
            System.out.println("\nNullPointerException capturada en main.");
        }
        System.out.println("\nFlujo esperado: a, e (finally), [NPE-caught en main]");
        System.out.println("Flujo real:    " + String.join(", ", executed));
        boolean valid = executed.contains("a") && executed.contains("e")
                && !executed.contains("b") && !executed.contains("c")
                && !executed.contains("d") && !executed.contains("f");
        System.out.println("Validación:    " + (valid ? "OK" : "ERROR"));
    }

    public static void doThings() {
        try {
            a();  // Lanza NullPointerException
            b();  // No se ejecuta
        } catch (NoSuchFileException x) {
            c();
        } catch (IOException y) {
            d();
        } finally {
            e();  // Sí se ejecuta
        }
        f();  // No se ejecuta - excepción se propaga
    }

    public static void a() throws IOException {
        executed.add("a");
        if (true) {
            throw new NullPointerException();  // Unchecked - no en throws
        }
        throw new IOException();
    }

    public static void b() {
        executed.add("b");
    }

    public static void c() {
        executed.add("c");
    }

    public static void d() {
        executed.add("d");
    }

    public static void e() {
        executed.add("e");
    }

    public static void f() {
        executed.add("f");
    }
}
