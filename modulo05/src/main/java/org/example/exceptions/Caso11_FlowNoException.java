package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * CASO 11: Normal Program Flow with No Exceptions
 *
 * Cuando NO se produce excepción:
 * - Flujo: a(), b(), e(), f()
 * - c() y d() NO se ejecutan (no hay catch)
 * - finally se ejecuta siempre
 */
public class Caso11_FlowNoException {

    private static final List<String> executed = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        doThings();
        System.out.println("\nFlujo esperado: a, b, e, f");
        System.out.println("Flujo real:    " + String.join(", ", executed));
        System.out.println("Validación:    " + (executed.equals(List.of("a", "b", "e", "f")) ? "OK" : "ERROR"));
    }

    public static void doThings() throws IOException {
        try {
            a();
            b();
        } catch (NoSuchFileException x) {
            c();
        } catch (IOException y) {
            d();
        } finally {
            e();
        }
        f();
    }

    public static void a() throws IOException {
        if (false) {
            throw new IOException();  // Nunca se ejecuta
        }
        executed.add("a");
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
