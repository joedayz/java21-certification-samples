package org.example.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 3: Streams conectados (Connecting Streams).
 *
 * Los streams se encadenan para añadir transformación, filtrado o buffering:
 *
 * Lectura:
 *   BufferedReader (readLine) -> InputStreamReader (bytes->chars) -> FileInputStream (origen)
 *
 * Escritura:
 *   PrintWriter (println) -> OutputStreamWriter (chars->bytes) -> FileOutputStream (destino)
 *
 * BufferedReader proporciona readLine(); PrintWriter proporciona println().
 */
public class Caso03_ConnectingStreams {

    public static void main(String[] args) throws IOException {
        Path baseDir = Path.of("modulo06-data");
        Files.createDirectories(baseDir);

        Path src = baseDir.resolve("some.txt");
        Path dst = baseDir.resolve("other.txt");

        String contenido = "Línea 1\nLínea 2\nLínea 3 con ñ";
        Files.writeString(src, contenido, StandardCharsets.UTF_8);

        Charset utf8 = StandardCharsets.UTF_8;

        System.out.println("=== Caso 3: Streams conectados (readLine / println) ===\n");

        copiarLineaALinea(src.toString(), dst.toString(), utf8);

        // readLine sin \n, println añade \n -> output = "L1\nL2\nL3\n"
        String esperado = "Línea 1\nLínea 2\nLínea 3 con ñ\n";
        String copiado = Files.readString(dst, utf8);
        boolean ok = esperado.equals(copiado);
        System.out.println("\nValidación: " + (ok ? "OK" : "Revisar"));
    }

    public static void copiarLineaALinea(String srcPath, String dstPath, Charset charset) throws IOException {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(srcPath), charset));
             PrintWriter out = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(dstPath), charset))) {
            String line;
            while ((line = in.readLine()) != null) {
                out.println(line);
                System.out.println("  Leído: " + line);
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
