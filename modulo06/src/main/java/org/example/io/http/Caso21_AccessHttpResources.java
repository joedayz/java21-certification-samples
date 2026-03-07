package org.example.io.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 21: Access HTTP Resources.
 *
 * java.net.http: HTTP Client API (Java 11+).
 * - Métodos: GET, POST, PUT, DELETE, HEAD, OPTIONS
 * - Autenticación, cifrado, proxies
 * - Modo síncrono y asíncrono
 * - HTTP/1.1 y HTTP/2
 *
 * Con módulos: requires java.net.http en module-info.java.
 * Ejemplo: descargar un documento HTML a un archivo local.
 */
public class Caso21_AccessHttpResources {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path base = Path.of("modulo06-data", "http-demo").toAbsolutePath();
        Path path = base.resolve("docs").resolve("index.html");
        Files.createDirectories(path.getParent());

        URI uri = URI.create("https://openjdk.org");
        HttpRequest req = HttpRequest.newBuilder(uri).GET().build();
        HttpClient client = HttpClient.newHttpClient();

        System.out.println("=== Caso 21: Access HTTP Resources ===\n");
        System.out.println("GET " + uri);
        System.out.println("Guardando en: " + path);

        HttpResponse<Path> res = client.send(req, HttpResponse.BodyHandlers.ofFile(path));

        System.out.println("Status: " + res.statusCode());
        System.out.println("Tamaño: " + Files.size(path) + " bytes");
        System.out.println("\nPrimeras líneas del archivo:");
        Files.lines(path).limit(5).forEach(line -> System.out.println("  " + line));
    }
}
