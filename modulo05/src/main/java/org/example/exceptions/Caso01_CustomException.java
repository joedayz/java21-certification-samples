package org.example.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 1: Excepciones custom - ProductException.
 * Demuestra los tres constructores y el exception chaining.
 */
public class Caso01_CustomException {

    private static final Logger logger = Logger.getLogger(Caso01_CustomException.class.getName());

    public static void main(String[] args) {
        System.out.println("=== Caso 1a: ProductException() sin argumentos ===\n");
        caso1a();

        System.out.println("\n=== Caso 1b: ProductException(String message) ===\n");
        caso1b();

        System.out.println("\n=== Caso 1c: ProductException(String, Throwable) - exception chaining ===\n");
        caso1c();
    }

    private static void caso1a() {
        try {
            throw new ProductException();
        } catch (ProductException e) {
            logger.log(Level.INFO, "ProductException sin mensaje capturada", e);
            System.out.println("Tipo: " + e.getClass().getSimpleName());
            System.out.println("Mensaje: " + (e.getMessage() != null ? e.getMessage() : "(null)"));
        }
    }

    private static void caso1b() {
        try {
            throw new ProductException("Producto con id 999 no encontrado");
        } catch (ProductException e) {
            logger.log(Level.WARNING, "ProductException con mensaje capturada", e);
            System.out.println("Mensaje: " + e.getMessage());
        }
    }

    private static void caso1c() {
        try {
            lanzarConCausa();
        } catch (ProductException e) {
            logger.log(Level.SEVERE, "ProductException con causa (chaining)", e);
            System.out.println("Mensaje: " + e.getMessage());
            System.out.println("Causa: " + e.getCause());
            if (e.getCause() != null) {
                System.out.println("  -> " + e.getCause().getMessage());
            }
        }
    }

    private static void lanzarConCausa() throws ProductException {
        try {
            Integer.parseInt("no-es-un-numero");
        } catch (NumberFormatException original) {
            throw new ProductException("Error al parsear producto: entrada inválida", original);
        }
    }
}
