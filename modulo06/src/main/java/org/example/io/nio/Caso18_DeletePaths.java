package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 18: Delete Paths.
 *
 * delete y deleteIfExists eliminan archivos y carpetas.
 * Borrar carpeta no vacía lanza excepción.
 * walk().sorted(reverseOrder()) para borrar recursivo (primero hijos, luego padres).
 */
public class Caso18_DeletePaths {

    private static final Logger logger = Logger.getLogger(Caso18_DeletePaths.class.getName());

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 18: Delete Paths ===\n");

        Path base = Path.of("modulo06-data", "to-delete").toAbsolutePath();
        Path backup = base.resolve("backup");
        Path docs = backup.resolve("docs");

        Files.createDirectories(docs);
        Files.writeString(docs.resolve("some.txt"), "x");
        Files.writeString(docs.resolve("other.txt"), "x");

        System.out.println("Creado: " + backup);

        Files.walk(backup)
                .sorted(Comparator.reverseOrder())
                .forEach(path -> {
                    try {
                        Files.delete(path);
                        System.out.println("  Deleted: " + path.getFileName());
                    } catch (IOException ex) {
                        logger.log(Level.SEVERE, "Error deleting file", ex);
                    }
                });

        System.out.println("\nCarpeta backup eliminada recursivamente.");
    }
}
