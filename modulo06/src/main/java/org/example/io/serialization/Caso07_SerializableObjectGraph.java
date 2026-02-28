package org.example.io.serialization;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

/**
 * CASO 7: Serializable Object Graph.
 *
 * - Serializable: permiso para serializar instancias.
 * - El grafo completo se serializa (date, items, Product/Drink/Food).
 * - transient: el campo NO se escribe en ObjectOutputStream (solo en memoria).
 * - SerializationException si un campo no-transient es de tipo no-serializable.
 */
public class Caso07_SerializableObjectGraph {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Path file = Path.of("modulo06-data", "caso07-graph");
        Files.createDirectories(file.getParent());

        PriceList list = new PriceList(LocalDate.of(2019, 4, 1));
        list.addItem(new Drink("Tea", 1.9));
        list.addItem(new Food("Cake", 3.5));

        System.out.println("=== Caso 7: Serializable Object Graph ===\n");
        System.out.println("Antes de serializar:");
        System.out.println("  date: " + list.getDate());
        System.out.println("  items: " + list.getItems().size());
        System.out.println("  hash (transient): " + list.getHash());

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file.toString()))) {
            out.writeObject(list);
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file.toString()))) {
            list = (PriceList) in.readObject();
        }

        System.out.println("\nDespués de deserializar:");
        System.out.println("  date: " + list.getDate());
        System.out.println("  items: " + list.getItems().size());
        System.out.println("  hash (transient): " + list.getHash() + " <- null, no se serializó");
        System.out.println("\nValidación: hash es null tras deserializar (transient)");
    }
}
