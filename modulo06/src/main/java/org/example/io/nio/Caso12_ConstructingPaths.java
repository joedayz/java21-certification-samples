package org.example.io.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 12: Constructing Filesystem Paths.
 *
 * Path: inmutable, var-arg Path.of(), absoluto o relativo.
 * Métodos: getFileName(), getParent(), resolve(), resolveSibling(),
 *          normalize(), toRealPath(), relativize().
 */
public class Caso12_ConstructingPaths {

    public static void main(String[] args) throws IOException {
        System.out.println("=== Caso 12: Constructing Paths ===\n");

        Path someFile = Path.of("/", "users", "joe", "docs", "some.txt");
        System.out.println("someFile: " + someFile);

        Path justSomeFile = someFile.getFileName();
        System.out.println("getFileName(): " + justSomeFile);

        Path docsFolder = someFile.getParent();
        System.out.println("getParent(): " + docsFolder);

        Path currentFolder = Path.of(".");
        System.out.println("Path.of('.'): " + currentFolder.toAbsolutePath().normalize());

        Path acmeFile = docsFolder.resolve("../pics/acme.jpg");
        System.out.println("resolve('../pics/acme.jpg'): " + acmeFile);

        Path normalisedAcmeFile = acmeFile.normalize();
        System.out.println("normalize(): " + normalisedAcmeFile);

        Path otherFile = someFile.resolveSibling("other.txt");
        System.out.println("resolveSibling('other.txt'): " + otherFile);

        // toRealPath() y relativize() requieren paths que existan - usamos paths relativos del workspace
        Path base = Path.of("modulo06-data").toAbsolutePath();
        Files.createDirectories(base);
        Path p1 = base.resolve("docs/some.txt");
        Path p2 = base.resolve("pics/acme.jpg");
        Files.createDirectories(p1.getParent());
        Files.createDirectories(p2.getParent());
        if (!Files.exists(p1)) Files.createFile(p1);
        if (!Files.exists(p2)) Files.createFile(p2);

        Path real1 = p1.toRealPath();
        Path real2 = p2.toRealPath();
        System.out.println("\ntoRealPath() p1: " + real1);

        Path between = p1.relativize(p2);
        System.out.println("relativize(p1->p2): " + between);
    }
}
