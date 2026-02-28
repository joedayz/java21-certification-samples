package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;

/**
 * CASO 14: Program Flow Catching Any Exceptions (IOException con catch genérico)
 *
 * Cuando se produce IOException en a():
 * - Flujo: a(), d() (catch Exception), e() (finally)
 * - f() NO se ejecuta porque el catch hace return
 * - El handler genérico (Exception) captura cualquier checked o unchecked
 * - El return termina el método, pero finally se ejecuta ANTES
 */
public class Caso14_FlowCatchingAnyException {

    private static final List<String> executed = new ArrayList<>();

    public static void main(String[] args) {
        doThings();
        System.out.println("\nFlujo esperado: a, d, e (f NO porque hay return en catch)");
        System.out.println("Flujo real:    " + String.join(", ", executed));
        System.out.println("Validación:    " + (executed.equals(List.of("a", "d", "e")) ? "OK" : "ERROR"));
        System.out.println("f no ejecutado: " + (!executed.contains("f") ? "OK" : "ERROR"));
    }

    public static void doThings() {
        try {
            a();  // Lanza IOException
            b();  // No se ejecuta
        } catch (NoSuchFileException x) {
            c();
        } catch (Exception y) {
            d();  // Catch genérico - captura IOException
            return;  // Termina el método - pero finally se ejecuta antes
        } finally {
            e();
        }
        f();  // NO se ejecuta - el return en catch terminó el método
    }

    public static void a() throws IOException {
        executed.add("a");
        if (true) {
            throw new IOException("Error de I/O");
        }
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
