package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 3: Catching exceptions.
 *
 * Reglas:
 * - try rodea código que puede lanzar excepciones
 * - catch específicos (subtipos) ANTES de los genéricos
 * - Excepciones no relacionadas pueden ir en multi-catch: catch (A | B e)
 * - finally se ejecuta siempre (limpieza de recursos)
 */
public class Caso03_CatchingExceptions {

    private static final Logger logger = Logger.getLogger(Caso03_CatchingExceptions.class.getName());

    public static void main(String[] args) {
        System.out.println("=== Caso 3a: Multi-catch (NullPointerException | ArithmeticException) ===\n");
        caso3a();

        System.out.println("\n=== Caso 3b: Orden específico -> genérico (NoSuchFile < IOException < Exception) ===\n");
        caso3b();

        System.out.println("\n=== Caso 3c: finally siempre se ejecuta ===\n");
        caso3c();
    }

    private static void caso3a() {
        try {
            // Simular lanzamiento - descomenta uno para probar
            throw new ArithmeticException("/ by zero");
            // throw new NullPointerException("value is null");
        } catch (NullPointerException | ArithmeticException e) {
            System.out.println("Multi-catch: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        } finally {
            System.out.println("finally ejecutado.");
        }
    }

    private static void caso3b() {
        try {
            doThingsQuePuedeFallar("nosuchfile");
        } catch (NoSuchFileException e) {
            logger.log(Level.SEVERE, "Archivo no encontrado", e);
            System.out.println("NoSuchFileException: " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error de I/O", e);
            System.out.println("IOException: " + e.getMessage());
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error genérico", e);
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static void caso3c() {
        try {
            System.out.println("Dentro del try");
            throw new RuntimeException("Error en try");
        } catch (RuntimeException e) {
            System.out.println("Capturado: " + e.getMessage());
        } finally {
            System.out.println("finally: se ejecuta aunque hubo excepción.");
        }
    }

    private static void doThingsQuePuedeFallar(String tipo) throws NoSuchFileException, IOException {
        if ("nosuchfile".equals(tipo)) {
            throw new NoSuchFileException("archivo.txt");
        }
    }
}
