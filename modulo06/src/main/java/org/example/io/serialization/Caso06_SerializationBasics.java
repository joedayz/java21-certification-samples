package org.example.io.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * CASO 6: Understand Serialization.
 *
 * Serialization: escribir objetos desde memoria a un stream (binario).
 * Deserialization: leer objetos desde un stream.
 *
 * ObjectOutputStream.writeObject() -> Destinos: Files, Network, Other JVMs
 * ObjectInputStream.readObject() <- Orígenes: Files, Network, Other JVMs
 */
public class Caso06_SerializationBasics {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path file = Path.of("modulo06-data", "caso06-swap");
        Files.createDirectories(file.getParent());

        PriceList list = new PriceList(LocalDate.now());
        list.addItem(new Drink("Tea", 1.9));
        list.addItem(new Food("Cake", 3.5));

        System.out.println("=== Caso 6: Serialization Basics ===\n");
        System.out.println("Antes: " + list);

        // Serializar a archivo
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toString()))) {
            out.writeObject(list);
        }
        System.out.println("Objeto escrito en " + file);

        list = null;
        System.out.println("Referencia limpiada: list = null");

        // Deserializar desde archivo
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toString()))) {
            list = (PriceList) in.readObject();
        }
        System.out.println("Objeto leído desde archivo");
        System.out.println("Después: " + list);
        System.out.println("\nValidación: OK");
    }
}
