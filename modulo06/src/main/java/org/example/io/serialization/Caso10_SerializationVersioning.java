package org.example.io.serialization;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CASO 10: Serialization and Versioning.
 *
 * ObjectInputStream verifica serialVersionUID. Si no coincide con la definición
 * actual de la clase -> InvalidClassException.
 *
 * No usar serialización para almacenamiento a largo plazo (cambios en código o JDK).
 * Alternativas: XML/JSON (JAXB, JSON-P) o bases de datos (JPA).
 *
 * Demo en dos pasos:
 * 1) Primera ejecución: serializa ProductVersioned (UID=1) a archivo
 * 2) Cambiar ProductVersioned.serialVersionUID a 2L, recompilar, ejecutar de nuevo
 *    -> InvalidClassException
 */
public class Caso10_SerializationVersioning {

    private static final Path FILE = Path.of("modulo06-data", "product-versioned.ser");

    public static void main(String[] args) {
        try {
            Files.createDirectories(FILE.getParent());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("=== Caso 10: Serialization and Versioning ===\n");

        if (!Files.exists(FILE)) {
            serializeFirst();
        } else {
            deserializeAndCheck();
        }
    }

    private static void serializeFirst() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE.toString()))) {
            ProductVersioned p = new ProductVersioned("Test", BigDecimal.ONE);
            out.writeObject(p);
            System.out.println("Archivo creado: " + FILE);
            System.out.println("\nPara ver InvalidClassException:");
            System.out.println("1. Cambia ProductVersioned.serialVersionUID a 2L");
            System.out.println("2. mvn compile");
            System.out.println("3. Ejecuta este caso de nuevo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deserializeAndCheck() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE.toString()))) {
            ProductVersioned p = (ProductVersioned) in.readObject();
            System.out.println("Deserialización OK: " + p.getName());
            System.out.println("(serialVersionUID coincide)");
        } catch (InvalidClassException e) {
            System.out.println("InvalidClassException (esperado si UID cambió):");
            System.out.println("  " + e.getMessage());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
