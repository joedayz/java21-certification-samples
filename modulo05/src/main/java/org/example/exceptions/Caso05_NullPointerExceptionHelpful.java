package org.example.exceptions;

/**
 * CASO 5: NullPointerException con mensajes útiles (Java 14+).
 *
 * Desde Java 14, NullPointerException incluye:
 * - Qué operación falló: "Cannot invoke String.indexOf(String)"
 * - Qué variable era null: "because Test.value is null"
 *
 * Opción -XX:-ShowCodeDetailsInExceptionMessages para desactivar (seguridad).
 */
public class Caso05_NullPointerExceptionHelpful {

    private static String value;  // null por defecto

    public static void main(String[] args) {
        // Java 14+: Cannot invoke "String.indexOf(String)" because "value" is null
        // Pre-14: solo NullPointerException sin detalle
        System.out.println(value.indexOf("a"));
    }
}
