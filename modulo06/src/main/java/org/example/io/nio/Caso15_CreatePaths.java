package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

/**
 * CASO 15: Create Paths.
 *
 * Files: notExists, exists, createDirectory, createDirectories, createFile.
 * writeString, lines: lectura/escritura rápida de texto.
 */
public class Caso15_CreatePaths {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 15: Create Paths ===\n");

        Path base = Path.of("modulo06-data").toAbsolutePath();
        Path source = base.resolve("joe/docs");
        Path backup = base.resolve("joe/backup/docs");

        Files.createDirectories(source);

        if (Files.notExists(backup)) {
            Files.createDirectories(backup);
            System.out.println("createDirectories: " + backup);
        }

        Path readme = backup.resolve("../readme.txt").normalize();
        if (!Files.exists(readme)) {
            Files.createFile(readme);
        }
        Files.writeString(readme, "Backup time: " + Instant.now());
        System.out.println("writeString a " + readme.getFileName());

        System.out.println("\nlines() - contenido:");
        Files.lines(readme).forEach(line -> System.out.println("  " + line));
    }
}
