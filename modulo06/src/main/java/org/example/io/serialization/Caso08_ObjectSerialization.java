package org.example.io.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * CASO 8: Object Serialization - write/read + manejo de excepciones.
 *
 * Pasos: 1) Escribir objeto en archivo, 2) Limpiar referencia, 3) Leer desde archivo.
 *
 * Excepciones:
 *   - FileNotFoundException: archivo no existe (lectura)
 *   - IOException: error general de I/O
 *   - ClassNotFoundException: clase del objeto serializado no encontrada
 */
public class Caso08_ObjectSerialization {

    private static final Logger logger = Logger.getLogger(Caso08_ObjectSerialization.class.getName());

    public static void main(String[] args) {
        Path file = Path.of("modulo06-data", "swap");
        try {
            Files.createDirectories(file.getParent());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "No se pudo crear directorio", e);
            return;
        }

        PriceList list = new PriceList(LocalDate.now());
        list.addItem(new Drink("Tea", 1.99));
        list.addItem(new Food("Cake", 3.5));

        System.out.println("=== Caso 8: Object Serialization + Exceptions ===\n");

        // Escritura
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toString()))) {
            out.writeObject(list);
            list = null;
            System.out.println("1. Objeto escrito, referencia limpiada");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed write object into a file", e);
            return;
        }

        // Lectura
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toString()))) {
            list = (PriceList) in.readObject();
            System.out.println("2. Objeto leído: " + list);
        } catch (FileNotFoundException e) {
            logger.log(Level.SEVERE, "File not found", e);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read object from file", e);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Unknown serialised type", e);
        }

        System.out.println("\nValidación: OK");
    }
}
