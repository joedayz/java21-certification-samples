package org.example.io.serialization;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

/**
 * CASO 9: Serialization of Sensitive Information.
 *
 * La serialización escribe datos fuera del entorno seguro.
 * Proteger información: generar hash seguro o usar cifrado.
 *
 * Ejemplo: generateHash con SHA-256
 * 1) Serializar objeto a ByteArrayOutputStream
 * 2) Obtener bytes serializados
 * 3) Generar digest SHA-256
 * 4) Convertir a String hexadecimal
 */
public class Caso09_SerializationSensitiveInfo {

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        PriceList list = new PriceList(LocalDate.now());
        list.addItem(new Drink("Tea", 1.9));
        list.addItem(new Food("Cake", 3.5));

        System.out.println("=== Caso 9: Serialization Sensitive Info (SHA-256) ===\n");

        String hash1 = generateHash(list);
        System.out.println("Hash 1: " + hash1);

        String hash2 = generateHash(list);
        System.out.println("Hash 2: " + hash2);

        System.out.println("\nMismo objeto -> mismo hash: " + hash1.equals(hash2));

        PriceList other = new PriceList(LocalDate.now());
        other.addItem(new Drink("Coffee", 2.5));
        String hash3 = generateHash(other);
        System.out.println("Hash 3 (otro objeto): " + hash3);
        System.out.println("Objetos diferentes -> hashes diferentes: " + !hash1.equals(hash3));
    }

    public static String generateHash(Object obj) throws NoSuchAlgorithmException, IOException {
        try (ByteArrayOutputStream byteArrayStream = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(byteArrayStream)) {
            out.writeObject(obj);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(byteArrayStream.toByteArray());
            return new BigInteger(1, digest).toString(16);
        }
    }
}
