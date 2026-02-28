package org.example.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 10: Suppressed exceptions.
 *
 * Durante el auto-cierre (close), si ya hay una excepción en el try:
 * - La excepción del try es la principal
 * - La excepción de close() se "suprime" (suppressed)
 * - getSuppressed() retorna el array de excepciones suprimidas
 */
public class Caso10_SuppressedExceptions {

    private static final Logger logger = Logger.getLogger(Caso10_SuppressedExceptions.class.getName());

    public static void main(String[] args) {
        try (SomeResource r = new SomeResource()) {
            r.doThings(true);  // lanza "Action failed"
            // close() se llama implícitamente y lanza "Closure failed"
            // -> "Closure failed" queda como suppressed
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Exception encountered:", ex);
            System.out.println("Excepción principal: " + ex.getMessage());

            Throwable[] suppressed = ex.getSuppressed();
            System.out.println("Excepciones suprimidas: " + suppressed.length);
            for (Throwable s : suppressed) {
                logger.log(Level.SEVERE, "Suppressed Exception:", s);
                System.out.println("  -> " + s.getMessage());
            }
        }
    }
}
