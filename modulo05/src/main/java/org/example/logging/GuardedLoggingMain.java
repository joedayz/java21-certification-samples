package org.example.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Guarded Logging: evita procesar mensajes que serán descartados.
 *
 * Problema: Si el nivel del logger es INFO y hacemos:
 *   logger.log(Level.FINE, "Product " + id + " has been selected");
 * La concatenación del String se ejecuta SIEMPRE, aunque el mensaje no se registre.
 *
 * Soluciones:
 *   1. isLoggable() - verifica antes de concatenar
 *   2. Object parameters con {0}, {1}... - la concatenación solo ocurre si se va a registrar
 */
public class GuardedLoggingMain {

    private static final Logger logger = Logger.getLogger(GuardedLoggingMain.class.getName());

    public static void main(String[] args) {
        long productId = 12345L;

        // (1) Establecer nivel a INFO
        logger.setLevel(Level.INFO);
        System.out.println("=== Logger nivel: INFO ===\n");

        // (2) MALA PRÁCTICA: concatenación siempre se ejecuta, aunque FINE < INFO
        logger.log(Level.FINE, "Product " + productId + " has been selected");
        System.out.println("La concatenación de arriba se ejecutó aunque el mensaje no se mostró.");

        // (3) GUARDED LOGGING con isLoggable: evita la concatenación si no se va a registrar
        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "Product " + productId + " has been selected");
        }
        System.out.println("Con isLoggable, la concatenación no se ejecutó.");

        // (4) MEJOR PRÁCTICA: Object parameters - la concatenación solo ocurre si se registra
        logger.log(Level.FINE, "Product {0} has been selected", productId);
        System.out.println("Con parámetros {0}, la concatenación se difiere al logger.\n");

        // Ahora cambiamos a FINE para ver los mensajes
        System.out.println("=== Cambiando nivel a FINE ===\n");
        logger.setLevel(Level.FINE);

        // Ahora sí se ven los mensajes FINE
        logger.log(Level.FINE, "Product " + productId + " has been selected");

        if (logger.isLoggable(Level.FINE)) {
            logger.log(Level.FINE, "[Guarded] Product " + productId + " has been selected");
        }

        logger.log(Level.FINE, "Product {0} has been selected", productId);

        // Ejemplo con múltiples parámetros
        String user = "jdiaz";
        String action = "compra";
        logger.log(Level.INFO, "Usuario {0} realizó acción: {1} del producto {2}",
                new Object[]{user, action, productId});

        System.out.println("\n=== Comparación de rendimiento ===");
        demostrarRendimiento(productId);
    }

    private static void demostrarRendimiento(long id) {
        logger.setLevel(Level.INFO);
        int iterations = 1_000_000;

        // Sin guarded logging - concatena siempre
        long start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            logger.log(Level.FINE, "Product " + id + " iteration " + i);
        }
        long sinGuard = System.nanoTime() - start;

        // Con guarded logging - no concatena si nivel es INFO
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            if (logger.isLoggable(Level.FINE)) {
                logger.log(Level.FINE, "Product " + id + " iteration " + i);
            }
        }
        long conGuard = System.nanoTime() - start;

        // Con parámetros de objeto
        start = System.nanoTime();
        for (int i = 0; i < iterations; i++) {
            logger.log(Level.FINE, "Product {0} iteration {1}", new Object[]{id, i});
        }
        long conParams = System.nanoTime() - start;

        logger.setLevel(Level.INFO);
        logger.info(String.format("Sin guard:     %,d ns", sinGuard));
        logger.info(String.format("Con guard:     %,d ns", conGuard));
        logger.info(String.format("Con params:    %,d ns", conParams));
    }
}
