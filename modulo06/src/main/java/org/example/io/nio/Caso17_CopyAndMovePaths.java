package org.example.io.nio;

import java.io.IOException;
import java.nio.file.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 17: Copy and Move Paths.
 *
 * copy: crea réplica. Copiar carpeta NO copia contenido.
 * move: elimina origen tras copiar. Mover carpeta SÍ mueve todo.
 *
 * StandardCopyOption: COPY_ATTRIBUTES, REPLACE_EXISTING, ATOMIC_MOVE.
 */
public class Caso17_CopyAndMovePaths {

    private static final Logger logger = Logger.getLogger(Caso17_CopyAndMovePaths.class.getName());

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 17: Copy and Move Paths ===\n");

        Path base = Path.of("modulo06-data", "copy-move").toAbsolutePath();
        Path source = base.resolve("docs");
        Path backup = base.resolve("backup");
        Path archive = base.resolve("archive");

        Files.createDirectories(source);
        Files.writeString(source.resolve("some.txt"), "archivo 1");
        Files.writeString(source.resolve("other.txt"), "archivo 2");

        System.out.println("Estructura inicial: docs/some.txt, docs/other.txt");

        Files.createDirectories(backup);
        Files.list(source).forEach(file -> {
            try {
                Files.copy(file, backup.resolve(file.getFileName()),
                        StandardCopyOption.COPY_ATTRIBUTES,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error copying file", ex);
            }
        });
        System.out.println("Copy: archivos copiados a backup/");

        Files.createDirectories(archive);
        Files.move(backup, archive.resolve("backup"),
                StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Move: backup movido a archive/backup");

        System.out.println("\nContenido archive/backup: " + Files.list(archive.resolve("backup")).toList());
    }
}
