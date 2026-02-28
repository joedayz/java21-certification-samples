package org.example.exceptions;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 9: try-with-resources - auto-cierre de recursos.
 *
 * Recursos que implementan AutoCloseable se cierran automáticamente.
 * Se puede usar con múltiples recursos.
 * El cierre ocurre en orden inverso al declarado (implícito finally).
 */
public class Caso09_TryWithResources {

    private static final Logger logger = Logger.getLogger(Caso09_TryWithResources.class.getName());

    public static void main(String[] args) {
        System.out.println("=== Caso 9a: FileNotFoundException (archivo no existe) ===\n");
        caso9a();

        System.out.println("\n=== Caso 9b: Lectura/escritura exitosa con try-with-resources ===\n");
        caso9b();
    }

    private static void caso9a() {
        try (BufferedReader in = new BufferedReader(new FileReader("no-existe.txt"))) {
            String text = in.readLine();
            System.out.println(text);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Opening file error", ex);
            System.out.println("FileNotFoundException: " + ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Read-write error", ex);
            System.out.println("IOException: " + ex.getMessage());
        }
        System.out.println("Recursos cerrados automáticamente.");
    }

    private static void caso9b() {
        // Crear archivo de entrada para la demo
        try (PrintWriter pw = new PrintWriter(new FileWriter("modulo05-demo-input.txt"))) {
            pw.println("Línea de prueba para try-with-resources");
        } catch (IOException e) {
            System.err.println("No se pudo crear archivo de demo: " + e.getMessage());
            return;
        }

        try (BufferedReader in = new BufferedReader(new FileReader("modulo05-demo-input.txt"));
             PrintWriter out = new PrintWriter(new FileWriter("modulo05-demo-output.txt"))) {
            String text = in.readLine();
            out.println(text);
            System.out.println("Leído y escrito: " + text);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Opening file error", ex);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Read-write error", ex);
        }
        System.out.println("Ambos recursos cerrados automáticamente.");
    }
}
