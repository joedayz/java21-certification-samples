package org.example.logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Configuración de logging con logging.properties.
 *
 * Dos formas de cargar la configuración:
 *   1. JVM argument: -Djava.util.logging.config.file=src/main/resources/logging.properties
 *   2. Programáticamente con LogManager.readConfiguration()
 *
 * Propiedades comunes en logging.properties:
 *   - handlers: handlers globales (ConsoleHandler, FileHandler, etc.)
 *   - .level: nivel global por defecto
 *   - [paquete].level: nivel para un paquete específico
 *   - [paquete].handlers: handlers para un paquete específico
 *   - java.util.logging.ConsoleHandler.formatter: formatter del ConsoleHandler
 *   - java.util.logging.FileHandler.pattern: patrón del nombre de archivo (%h=home, %u=único)
 *   - java.util.logging.FileHandler.limit: tamaño máximo del archivo en bytes
 *   - java.util.logging.FileHandler.count: número de archivos de rotación
 */
public class LoggingConfigMain {

    private static final Logger logger = Logger.getLogger(LoggingConfigMain.class.getName());

    public static void main(String[] args) {
        System.out.println("=== 1. Configuración por defecto ===\n");
        mostrarConfiguracionActual();

        System.out.println("\n=== 2. Cargando logging.properties programáticamente ===\n");
        cargarConfiguracion();
        mostrarConfiguracionActual();

        System.out.println("\n=== 3. Probando la configuración cargada ===\n");
        logger.severe("Mensaje SEVERE");
        logger.warning("Mensaje WARNING");
        logger.info("Mensaje INFO");
        logger.config("Mensaje CONFIG");
        logger.fine("Mensaje FINE");
        logger.finer("Mensaje FINER");
        logger.finest("Mensaje FINEST");
    }

    private static void cargarConfiguracion() {
        try (InputStream is = LoggingConfigMain.class
                .getClassLoader()
                .getResourceAsStream("logging.properties")) {
            if (is != null) {
                LogManager.getLogManager().readConfiguration(is);
                System.out.println("logging.properties cargado exitosamente.");
            } else {
                System.out.println("No se encontró logging.properties en el classpath.");
            }
        } catch (IOException e) {
            System.err.println("Error al cargar configuración: " + e.getMessage());
        }
    }

    private static void mostrarConfiguracionActual() {
        Logger rootLogger = Logger.getLogger("");
        Logger pkgLogger = Logger.getLogger("org.example.logging");

        System.out.println("Root logger:");
        System.out.println("  Nivel: " + rootLogger.getLevel());
        System.out.println("  Handlers: " + rootLogger.getHandlers().length);
        for (var h : rootLogger.getHandlers()) {
            System.out.println("    - " + h.getClass().getSimpleName()
                    + " [nivel=" + h.getLevel()
                    + ", formatter=" + h.getFormatter().getClass().getSimpleName() + "]");
        }

        System.out.println("Logger 'org.example.logging':");
        System.out.println("  Nivel propio: " + pkgLogger.getLevel());
        System.out.println("  isLoggable(FINE): " + pkgLogger.isLoggable(Level.FINE));
        System.out.println("  Handlers: " + pkgLogger.getHandlers().length);
        for (var h : pkgLogger.getHandlers()) {
            System.out.println("    - " + h.getClass().getSimpleName()
                    + " [nivel=" + h.getLevel()
                    + ", formatter=" + h.getFormatter().getClass().getSimpleName() + "]");
        }
    }
}
