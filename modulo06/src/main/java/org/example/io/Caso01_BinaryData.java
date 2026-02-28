package org.example.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 1: Lectura y escritura de datos binarios (InputStream / OutputStream).
 *
 * Clases abstractas: InputStream, OutputStream.
 * Métodos principales:
 *   - read(byte[] buffer, int offset, int length): lee bytes
 *   - write(byte[] buffer, int offset, int length): escribe bytes
 *   - close(): cierra el stream
 *
 * Comportamiento de read():
 *   - Lecturas intermedias: retorna buffer.length (buffer lleno)
 *   - Penúltima lectura: retorna bytes restantes (< buffer.length)
 *   - Última lectura: retorna -1 (fin del stream)
 *
 * AutoCloseable: usar try-with-resources para cierre automático.
 */
public class Caso01_BinaryData {

    public static void main(String[] args) throws IOException {
        Path baseDir = Path.of("modulo06-data");
        Files.createDirectories(baseDir);

        Path src = baseDir.resolve("some.bin");
        Path dst = baseDir.resolve("other.bin");

        // Crear archivo fuente con datos binarios de ejemplo
        byte[] original = "Hello binary world! 0123456789".getBytes();
        Files.write(src, original);

        System.out.println("=== Caso 1: Lectura/escritura binaria ===\n");
        System.out.println("Origen: " + src);
        System.out.println("Destino: " + dst);

        copiarBinario(src.toString(), dst.toString());

        byte[] copiado = Files.readAllBytes(dst);
        boolean ok = java.util.Arrays.equals(original, copiado);
        System.out.println("\nValidación: " + (ok ? "OK (archivos idénticos)" : "ERROR"));
    }

    public static void copiarBinario(String srcPath, String dstPath) throws IOException {
        try (InputStream in = new FileInputStream(srcPath);
             OutputStream out = new FileOutputStream(dstPath)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
