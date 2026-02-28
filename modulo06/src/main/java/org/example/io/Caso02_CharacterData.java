package org.example.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 2: Lectura y escritura de datos de caracteres (Reader / Writer).
 *
 * Clases abstractas: Reader, Writer.
 * Métodos principales:
 *   - read(char[] buffer, int offset, int length): lee caracteres
 *   - write(char[] buffer, int offset, int length): escribe caracteres
 *   - flush(), close()
 *
 * read() retorna: longitud leída, o -1 al final del stream.
 *
 * FileReader/FileWriter permiten especificar Charset (ej. UTF-8).
 */
public class Caso02_CharacterData {

    public static void main(String[] args) throws IOException {
        Path baseDir = Path.of("modulo06-data");
        Files.createDirectories(baseDir);

        Path src = baseDir.resolve("some.txt");
        Path dst = baseDir.resolve("other.txt");

        Charset utf8 = StandardCharsets.UTF_8;
        String texto = "Hola mundo con ñ y acentos: café, año, mañana";

        Files.writeString(src, texto, utf8);

        System.out.println("=== Caso 2: Lectura/escritura caracteres (UTF-8) ===\n");
        System.out.println("Texto: " + texto);

        copiarCaracteres(src.toString(), dst.toString(), utf8);

        String copiado = Files.readString(dst, utf8);
        boolean ok = texto.equals(copiado);
        System.out.println("\nValidación: " + (ok ? "OK (texto idéntico)" : "ERROR"));
    }

    public static void copiarCaracteres(String srcPath, String dstPath, Charset charset) throws IOException {
        try (Reader in = new FileReader(srcPath, charset);
             Writer out = new FileWriter(dstPath, charset)) {
            char[] buffer = new char[1024];
            int length;
            while ((length = in.read(buffer)) != -1) {
                out.write(buffer, 0, length);
            }
        } catch (IOException e) {
            throw e;
        }
    }
}
