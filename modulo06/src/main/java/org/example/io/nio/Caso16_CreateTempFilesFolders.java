package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 16: Create Temporary Files and Folders.
 *
 * createTempDirectory(prefix), createTempFile(parent, prefix, suffix).
 * Los temporales van al directorio temporal por defecto.
 * deleteIfExists para limpiar.
 */
public class Caso16_CreateTempFilesFolders {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 16: Create Temp Files and Folders ===\n");

        Path p1 = Files.createTempDirectory("TEMP");
        System.out.println("createTempDirectory: " + p1);

        Path p2 = Files.createTempFile(p1, "TEMP", ".tmp");
        System.out.println("createTempFile: " + p2);

        Files.deleteIfExists(p2);
        Files.deleteIfExists(p1);
        System.out.println("\nEliminados con deleteIfExists.");
    }
}
