package org.example.logging;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Ejemplo básico de la Java Logging API.
 *
 * Cada Logger se identifica por un nombre (normalmente el nombre de la clase).
 * Hay 7 niveles de logging (de mayor a menor severidad):
 *   SEVERE > WARNING > INFO > CONFIG > FINE > FINER > FINEST
 *
 * Por defecto el nivel es INFO, por lo que solo se muestran SEVERE, WARNING e INFO.
 */
public class LoggingBasicoMain {

    private static final Logger logger = Logger.getLogger(LoggingBasicoMain.class.getName());

    public static void main(String[] args) {

        System.out.println("=== 1. Logging con métodos de conveniencia ===");
        logger.severe("Esto es un mensaje SEVERE");
        logger.warning("Esto es un mensaje WARNING");
        logger.info("Esto es un mensaje INFO");
        logger.config("Esto es un mensaje CONFIG (no se ve con nivel INFO)");
        logger.fine("Esto es un mensaje FINE (no se ve con nivel INFO)");
        logger.finer("Esto es un mensaje FINER (no se ve con nivel INFO)");
        logger.finest("Esto es un mensaje FINEST (no se ve con nivel INFO)");

        System.out.println("\n=== 2. Logging con logger.log(Level, String) ===");
        logger.log(Level.SEVERE, "Error crítico en el sistema");
        logger.log(Level.WARNING, "Advertencia: recurso casi agotado");
        logger.log(Level.INFO, "Operación completada exitosamente");
        logger.log(Level.FINE, "Detalle fino (no visible por defecto)");

        System.out.println("\n=== 3. Logging de excepciones ===");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.log(Level.SEVERE, "Error aritmético detectado", e);
        }

        System.out.println("\n=== 4. Cambiando el nivel a FINE para ver más mensajes ===");
        logger.setLevel(Level.FINE);
        logger.severe("SEVERE: visible");
        logger.warning("WARNING: visible");
        logger.info("INFO: visible");
        logger.config("CONFIG: visible con nivel FINE");
        logger.fine("FINE: visible con nivel FINE");
        logger.finer("FINER: no visible con nivel FINE");
        logger.finest("FINEST: no visible con nivel FINE");

        System.out.println("\n=== 5. Métodos entering/exiting/throwing (nivel FINER) ===");
        logger.setLevel(Level.FINER);


        for (Handler h : logger.getParent().getHandlers()) {
            h.setLevel(Level.FINER); // Nivel del handler
        }


        procesarOrden("ORD-001");
    }

    private static void procesarOrden(String orderId) {
        logger.entering(LoggingBasicoMain.class.getName(), "procesarOrden", orderId);

        logger.fine("Procesando orden: " + orderId);

        if (orderId.startsWith("ORD")) {
            logger.exiting(LoggingBasicoMain.class.getName(), "procesarOrden", "OK");
            return;
        }

        Exception ex = new IllegalArgumentException("Orden inválida: " + orderId);
        logger.throwing(LoggingBasicoMain.class.getName(), "procesarOrden", ex);
    }
}
