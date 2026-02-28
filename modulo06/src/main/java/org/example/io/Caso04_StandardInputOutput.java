package org.example.io;

import java.util.Scanner;

/**
 * CASO 4: Standard Input and Output.
 *
 * Clase System - referencias a los streams estándar:
 *   - System.in:  InputStream  (entrada estándar, típicamente teclado)
 *   - System.out: PrintStream  (salida estándar)
 *   - System.err: PrintStream  (salida de error estándar)
 *
 * java.util.Scanner: facilita el parseo de entrada (nextLine(), nextInt(), etc.)
 *
 * Programa Echo: lee líneas y las imprime hasta que el usuario escriba "exit".
 * Ejecutar desde terminal para entrada interactiva.
 */
public class Caso04_StandardInputOutput {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String txt;

        System.out.println("To quit type: exit");
        System.out.println("Type value and press enter:");

        while (!(txt = s.nextLine()).equals("exit")) {
            System.out.println("Echo: " + txt);
        }
    }
}
