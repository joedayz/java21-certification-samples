package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * CASO 13: Program Flow Catching Specific Checked Exception (NoSuchFileException)
 *
 * Cuando se produce NoSuchFileException en a():
 * - Flujo: a(), c() (catch NoSuchFileException), e() (finally), f()
 * - El catch específico intercepta antes que IOException
 * - d() NO se ejecuta (el catch IOException no aplica)
 * - El programa continúa normalmente tras el try-catch-finally
 */
public class Caso13_FlowCatchingSpecificChecked {

    private static final List<String> executed = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        doThings();
        System.out.println("\nFlujo esperado: a, c, e, f");
        System.out.println("Flujo real:    " + String.join(", ", executed));
        System.out.println("Validación:    " + (executed.equals(List.of("a", "c", "e", "f")) ? "OK" : "ERROR"));
    }

    public static void doThings() throws IOException {
        try {
            a();  // Lanza NoSuchFileException
            b();  // No se ejecuta
        } catch (NoSuchFileException x) {
            c();  // Catch específico - se ejecuta
        } catch (IOException y) {
            d();  // No se ejecuta - NoSuchFileException ya capturada
        } finally {
            e();
        }
        f();  // El programa continúa
    }

    public static void a() throws IOException {
        executed.add("a");
        if (true) {
            throw new NoSuchFileException("archivo.txt");
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
