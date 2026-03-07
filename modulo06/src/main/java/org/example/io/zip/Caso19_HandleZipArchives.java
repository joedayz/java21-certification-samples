package org.example.io.zip;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * CASO 19: Handle Zip Archives.
 *
 * ZipOutputStream / ZipInputStream para leer y escribir archivos zip.
 * ZipEntry: crear y extraer entradas.
 *
 * Ejemplo: comprimir el contenido de un directorio (joe) en joe.zip.
 * - Files.walk para recorrer solo archivos (no directorios)
 * - relativize para el path relativo dentro del zip
 * - putNextEntry, write(bytes), closeEntry por cada archivo
 */
public class Caso19_HandleZipArchives {

    private static final Logger logger = Logger.getLogger(Caso19_HandleZipArchives.class.getName());

    public static void main(String[] args) throws IOException {
        Path base = Path.of("modulo06-data", "zip-demo").toAbsolutePath();
        Path joe = base.resolve("joe");
        Path zip = base.resolve("joe.zip");

        Files.createDirectories(joe.resolve("docs"));
        Files.createDirectories(joe.resolve("pics"));
        Files.writeString(joe.resolve("docs/some.txt"), "Contenido some.txt");
        Files.writeString(joe.resolve("docs/other.txt"), "Contenido other.txt");
        Files.writeString(joe.resolve("pics/acme.jpg"), "fake image bytes");

        System.out.println("=== Caso 19: Handle Zip Archives ===\n");
        System.out.println("Origen: " + joe);
        System.out.println("Zip: " + zip);

        if (Files.exists(zip)) {
            Files.delete(zip);
        }
        Files.createFile(zip);

        try (ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(zip))) {
            out.setLevel(Deflater.DEFAULT_COMPRESSION);

            Files.walk(joe)
                    .filter(p -> !Files.isDirectory(p))
                    .forEach(p -> {
                        ZipEntry zipEntry = new ZipEntry(joe.relativize(p).toString().replace('\\', '/'));
                        try {
                            out.putNextEntry(zipEntry);
                            out.write(Files.readAllBytes(p));
                            out.closeEntry();
                            System.out.println("  Añadido: " + zipEntry.getName());
                        } catch (IOException e) {
                            logger.log(Level.SEVERE, "Error creating zip entry", e);
                        }
                    });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating zip archive", e);
        }

        System.out.println("\nArchivo creado: " + zip + " (" + Files.size(zip) + " bytes)");
    }
}
