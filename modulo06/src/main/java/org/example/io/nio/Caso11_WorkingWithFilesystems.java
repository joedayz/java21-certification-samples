package org.example.io.nio;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * CASO 11: Working with Filesystems.
 *
 * Package java.nio.file:
 *   - Path: representa archivos y carpetas
 *   - Files: operaciones sobre Path
 *   - FileSystem: sistema de archivos
 *
 * FileSystem: FileStore, root directories, separator.
 * java.io.File es legacy; Path.toPath() y File.toPath() para interoperar.
 */
public class Caso11_WorkingWithFilesystems {

    public static void main(String[] args) {
        System.out.println("=== Caso 11: Working with Filesystems ===\n");

        FileSystem fs = FileSystems.getDefault();

        System.out.println("FileStores (type + name):");
        fs.getFileStores().forEach(s -> System.out.println("  " + s.type() + " " + s.name()));

        System.out.println("\nRoot directories:");
        fs.getRootDirectories().forEach(p -> System.out.println("  " + p));

        String separator = fs.getSeparator();
        System.out.println("\nPath separator: '" + separator + "'");
    }
}
