package org.example.exceptions;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 8: Handling exceptions - logging, rethrow, cleanup.
 *
 * En catch podemos:
 * - Escribir logs
 * - Lanzar otra excepción (rethrowing)
 * - Retornar y terminar el método
 *
 * En finally:
 * - Cerrar recursos (cleanup)
 * - Se ejecuta siempre, aunque haya excepción
 */
public class Caso08_HandlingWithLogging {

    private static final Logger logger = Logger.getLogger(Caso08_HandlingWithLogging.class.getName());

    public static void main(String[] args) {
        try {
            leerArchivo("no-existe.txt");
        } catch (ProductException e) {
            logger.log(Level.SEVERE, "Error leyendo archivo", e);
            System.out.println("ProductException propagada: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("  Causa: " + e.getCause().getMessage());
            }
        }
    }

    private static void leerArchivo(String path) throws ProductException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(path));
            String text = in.readLine();
            System.out.println(text);
        } catch (FileNotFoundException ex) {
            logger.log(Level.SEVERE, "Error opening file", ex);
            return;  // Terminar el método
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error reading file", ex);
            throw new ProductException("Failed to read text", ex);  // Rethrow envuelta
        } finally {
            try {
                if (in != null) {
                    in.close();  // Cleanup
                }
            } catch (IOException ex) {
                logger.log(Level.SEVERE, "Error closing file", ex);
            }
        }
    }
}
