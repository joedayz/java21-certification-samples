package org.example.logging;

import java.io.IOException;
import java.util.logging.*;

/**
 * Log Handlers, Formatters y jerarquía de Loggers.
 *
 * Jerarquía de loggers:
 *   Logger "" (root)          ← nivel por defecto
 *     └── Logger "org.example"
 *           └── Logger "org.example.logging" ← hereda nivel del padre
 *
 * Los log records pasan por filtros en el logger y en el handler.
 * Handlers escriben a distintos destinos: Console, File, Memory, Socket, Stream.
 * Formatters dan formato al mensaje: SimpleFormatter (texto) o XMLFormatter (XML).
 */
public class LogHandlersMain {

    public static void main(String[] args) throws IOException {
        System.out.println("=== 1. Jerarquía de Loggers ===\n");
        demostrarJerarquia();

        System.out.println("\n=== 2. Console Handler con SimpleFormatter ===\n");
        demostrarConsoleHandler();

        System.out.println("\n=== 3. File Handler con XMLFormatter ===\n");
        demostrarFileHandler();

        System.out.println("\n=== 4. Múltiples Handlers en un Logger ===\n");
        demostrarMultiplesHandlers();

        System.out.println("\n=== 5. Filtros en Logger y Handler ===\n");
        demostrarFiltros();
    }

    private static void demostrarJerarquia() {
        Logger rootLogger = Logger.getLogger("");
        Logger parentLogger = Logger.getLogger("org.example");
        Logger childLogger = Logger.getLogger("org.example.logging.Product");

        System.out.println("Root logger nivel: " + rootLogger.getLevel());
        System.out.println("Parent 'org.example' nivel: " + parentLogger.getLevel());
        System.out.println("Child 'org.example.logging.Product' nivel: " + childLogger.getLevel());

        // El hijo hereda del padre si no tiene nivel propio
        parentLogger.setLevel(Level.FINE);
        System.out.println("\nDespués de setLevel(FINE) en parent:");
        System.out.println("Parent nivel: " + parentLogger.getLevel());
        // getLevel() retorna null si hereda; getEffectiveLevel no existe,
        // pero isLoggable refleja el nivel efectivo
        System.out.println("Child nivel propio: " + childLogger.getLevel());
        System.out.println("Child isLoggable(FINE): " + childLogger.isLoggable(Level.FINE));

        // El hijo puede sobreescribir el nivel
        childLogger.setLevel(Level.SEVERE);
        System.out.println("\nDespués de setLevel(SEVERE) en child:");
        System.out.println("Child nivel: " + childLogger.getLevel());
        System.out.println("Child isLoggable(WARNING): " + childLogger.isLoggable(Level.WARNING));
        System.out.println("Child isLoggable(SEVERE): " + childLogger.isLoggable(Level.SEVERE));

        // Cleanup
        parentLogger.setLevel(null);
        childLogger.setLevel(null);
    }

    private static void demostrarConsoleHandler() {
        Logger logger = Logger.getLogger("demo.console");
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);

        logger.info("Mensaje INFO con SimpleFormatter en consola");
        logger.fine("Mensaje FINE con SimpleFormatter en consola");
        logger.warning("Mensaje WARNING con SimpleFormatter en consola");

        logger.removeHandler(consoleHandler);
    }

    private static void demostrarFileHandler() throws IOException {
        Logger logger = Logger.getLogger("demo.file");
        logger.setUseParentHandlers(false);

        // FileHandler escribe a archivo con XMLFormatter por defecto
        FileHandler fileHandler = new FileHandler("modulo05-demo.log", true);
        fileHandler.setLevel(Level.ALL);
        fileHandler.setFormatter(new XMLFormatter());

        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);

        logger.severe("Error crítico - guardado en archivo XML");
        logger.info("Info - guardado en archivo XML");
        logger.fine("Detalle fino - guardado en archivo XML");

        fileHandler.close();
        logger.removeHandler(fileHandler);
        System.out.println("Revisa el archivo 'modulo05-demo.log' para ver el formato XML.");

        // Ahora con SimpleFormatter en archivo
        FileHandler fileHandler2 = new FileHandler("modulo05-demo-simple.log", true);
        fileHandler2.setFormatter(new SimpleFormatter());
        fileHandler2.setLevel(Level.ALL);

        logger.addHandler(fileHandler2);

        logger.info("Info con SimpleFormatter en archivo");
        logger.warning("Warning con SimpleFormatter en archivo");

        fileHandler2.close();
        logger.removeHandler(fileHandler2);
        System.out.println("Revisa 'modulo05-demo-simple.log' para ver el formato simple.");
    }

    private static void demostrarMultiplesHandlers() throws IOException {
        Logger logger = Logger.getLogger("demo.multi");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        // Handler 1: Consola solo para WARNING+
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        consoleHandler.setFormatter(new SimpleFormatter());

        // Handler 2: Archivo para FINE+
        FileHandler fileHandler = new FileHandler("modulo05-multi.log", true);
        fileHandler.setLevel(Level.FINE);
        fileHandler.setFormatter(new SimpleFormatter());

        logger.addHandler(consoleHandler);
        logger.addHandler(fileHandler);

        logger.severe("SEVERE: va a consola Y archivo");
        logger.warning("WARNING: va a consola Y archivo");
        logger.info("INFO: va SOLO a archivo (consola filtra WARNING+)");
        logger.fine("FINE: va SOLO a archivo");

        fileHandler.close();
        logger.removeHandler(consoleHandler);
        logger.removeHandler(fileHandler);

        System.out.println("Revisa 'modulo05-multi.log' - tiene 4 mensajes.");
        System.out.println("La consola solo mostró SEVERE y WARNING.");
    }

    private static void demostrarFiltros() {
        Logger logger = Logger.getLogger("demo.filter");
        logger.setUseParentHandlers(false);
        logger.setLevel(Level.ALL);

        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter());

        // Filtro en el handler: solo mensajes que contienen "IMPORTANTE"
        handler.setFilter(record -> record.getMessage().contains("IMPORTANTE"));

        logger.addHandler(handler);

        logger.info("Este mensaje no pasa el filtro");
        logger.info("IMPORTANTE: este mensaje sí pasa el filtro");
        logger.warning("Otro mensaje filtrado");
        logger.warning("IMPORTANTE: advertencia que pasa el filtro");

        logger.removeHandler(handler);
    }
}
