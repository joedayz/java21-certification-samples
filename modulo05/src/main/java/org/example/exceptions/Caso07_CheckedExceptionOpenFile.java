package org.example.exceptions;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

/**
 * CASO 7: Checked exception - openFile con NoSuchFileException.
 *
 * Checked exceptions deben:
 * - Ser capturadas (try-catch), O
 * - Ser propagadas (throws en la firma)
 */
public class Caso07_CheckedExceptionOpenFile {

    public static void main(String[] args) {
        try {
            openFile(null);  // <- lanza NoSuchFileException
        } catch (IOException e) {
            System.out.println("IOException capturada: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void openFile(String fileName) throws IOException {
        if (fileName == null) {
            throw new NoSuchFileException("Filename must be set");
        }
        // ... abrir archivo
    }
}
