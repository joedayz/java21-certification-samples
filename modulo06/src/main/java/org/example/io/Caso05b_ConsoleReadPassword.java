package org.example.io;

import java.io.Console;

/**
 * CASO 5b: Console - readPassword().
 *
 * readPassword() lee sin mostrar caracteres en pantalla (para passwords).
 * Retorna char[] por seguridad (se puede sobrescribir en memoria).
 */
public class Caso05b_ConsoleReadPassword {

    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.out.println("Console is not supported - ejecuta desde terminal");
            return;
        }

        char[] password = c.readPassword("Enter password: ");
        if (password != null) {
            System.out.println("Password length: " + password.length);
            // Sobrescribir por seguridad
            java.util.Arrays.fill(password, ' ');
        }
    }
}
