package org.example.io.zip;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 20: Represent Zip Archive as a FileSystem.
 *
 * FileSystems.newFileSystem(zip) permite tratar un .zip como FileSystem:
 * crear, copiar, mover, eliminar, navegar dentro del archivo.
 *
 * Proveedor: módulo jdk.zipfs (incluido en el JDK).
 */
public class Caso20_ZipAsFileSystem {

    private static final Logger logger = Logger.getLogger(Caso20_ZipAsFileSystem.class.getName());

    public static void main(String[] args) throws IOException {
        Path base = Path.of("modulo06-data", "zip-demo").toAbsolutePath();
        Path joe = base.resolve("joe");
        Path zip = base.resolve("joe-fs.zip");

        Files.createDirectories(joe.resolve("docs"));
        Files.createDirectories(joe.resolve("pics"));
        Files.writeString(joe.resolve("docs/some.txt"), "Contenido some.txt");
        Files.writeString(joe.resolve("docs/other.txt"), "Contenido other.txt");
        Files.writeString(joe.resolve("pics/acme.jpg"), "fake image");

        System.out.println("=== Caso 20: Zip Archive as FileSystem ===\n");
        System.out.println("Origen: " + joe);
        System.out.println("Zip: " + zip);

        if (Files.exists(zip)) {
            Files.delete(zip);
        }

        var env = new HashMap<String, Object>();
        env.put("create", "true");
        try (FileSystem fs = FileSystems.newFileSystem(zip, env)) {
            Files.walk(joe).forEach(source -> {
                try {
                    Path relative = joe.relativize(source);
                    String targetPath = "/" + relative.toString().replace('\\', '/');
                    Path target = fs.getPath(targetPath);

                    if (Files.isDirectory(source)) {
                        if (!targetPath.equals("/")) {
                            Files.createDirectories(target);
                            System.out.println("  Dir: " + targetPath);
                        }
                    } else {
                        Files.createDirectories(target.getParent());
                        Files.copy(source, target);
                        System.out.println("  Copiado: " + targetPath);
                    }
                } catch (IOException e) {
                    logger.log(Level.SEVERE, "Error archiving file", e);
                }
            });
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error creating archive", e);
        }

        System.out.println("\nArchivo creado: " + zip + " (" + Files.size(zip) + " bytes)");
    }
}
