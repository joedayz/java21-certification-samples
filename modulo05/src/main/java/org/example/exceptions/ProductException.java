package org.example.exceptions;

/**
 * Excepción custom que extiende Exception (checked).
 * Debe extender Exception o una subclase más específica de Throwable.
 *
 * Constructores:
 * - sin argumentos
 * - con mensaje (para proporcionar detalle del error)
 * - con mensaje y causa (exception chaining - preservar causa original)
 */
public class ProductException extends Exception {

    public ProductException() {
        super();
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(String message, Throwable cause) {
        super(message, cause);
    }
}
