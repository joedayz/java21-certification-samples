package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 13: Navigating the Filesystem.
 *
 * Path como secuencia de elementos (getNameCount, getName(i)).
 * Files.list(): contenido directo de una carpeta.
 * Files.walk(): recorrido recursivo.
 * Symbolic links: createSymbolicLink, readSymbolicLink.
 */
public class Caso13_NavigatingFilesystem {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 13: Navigating the Filesystem ===\n");

        Path base = Path.of("modulo06-data", "joe").toAbsolutePath();
        Path joe = base;
        Path docs = joe.resolve("docs");
        Path pics = joe.resolve("pics");

        Files.createDirectories(docs);
        Files.createDirectories(pics);
        Files.writeString(docs.resolve("some.txt"), "content");
        Files.writeString(docs.resolve("other.txt"), "content");
        Files.writeString(pics.resolve("acme.jpg"), "image");

        Path p1 = docs.resolve("some.txt");
        System.out.println("Path p1: " + p1);
        System.out.println("Elementos (getNameCount/getName):");
        for (int i = 0; i < p1.getNameCount(); i++) {
            System.out.println("  " + i + ": " + p1.getName(i));
        }

        System.out.println("\nFiles.list(joe):");
        Files.list(joe).forEach(p -> System.out.println("  " + p.getFileName()));

        System.out.println("\nFiles.walk - archivos .txt:");
        Files.walk(joe)
                .map(Path::toString)
                .filter(s -> s.endsWith(".txt"))
                .forEach(s -> System.out.println("  " + s));

        Path symlink = pics.resolve("s.txt");
        if (!Files.exists(symlink)) {
            Files.createSymbolicLink(symlink, docs.resolve("some.txt"));
            System.out.println("\nSymbolic link creado: " + symlink + " -> some.txt");
        }
        Path target = Files.readSymbolicLink(symlink);
        System.out.println("readSymbolicLink: " + target);
    }
}
