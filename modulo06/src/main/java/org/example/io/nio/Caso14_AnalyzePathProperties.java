package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermissions;

/**
 * CASO 14: Analyze Path Properties.
 *
 * Files: isDirectory, isRegularFile, isReadable, isWritable, isExecutable,
 *       isHidden, isSymbolicLink, isSameFile, probeContentType.
 * PosixFileAttributes: size, creationTime, lastModifiedTime, lastAccessTime,
 *                      owner, group, permissions.
 */
public class Caso14_AnalyzePathProperties {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 14: Analyze Path Properties ===\n");

        Path base = Path.of("modulo06-data", "analyze").toAbsolutePath();
        Files.createDirectories(base);

        Path p1 = base.resolve("some.txt");
        Files.writeString(p1, "Hola mundo");
        Path p2 = base.resolve("./some.txt");  // Mismo archivo, path relativo

        System.out.println("p1: " + p1);
        System.out.println("  isDirectory: " + Files.isDirectory(p1));
        System.out.println("  isRegularFile: " + Files.isRegularFile(p1));
        System.out.println("  isReadable: " + Files.isReadable(p1));
        System.out.println("  isWritable: " + Files.isWritable(p1));
        System.out.println("  isExecutable: " + Files.isExecutable(p1));
        System.out.println("  probeContentType: " + Files.probeContentType(p1));

        System.out.println("\nisSameFile(p1, p2): " + Files.isSameFile(p1, p2));

        try {
            var fa = Files.readAttributes(p1, PosixFileAttributes.class);
            System.out.println("\nPosixFileAttributes:");
            System.out.println("  size: " + fa.size());
            System.out.println("  lastModifiedTime: " + fa.lastModifiedTime());
            System.out.println("  owner: " + fa.owner().getName());
            var perms = fa.permissions();
            System.out.println("  permissions: " + PosixFilePermissions.toString(perms));
        } catch (UnsupportedOperationException e) {
            System.out.println("\nPosixFileAttributes no soportado (ej. Windows)");
        }
    }
}
