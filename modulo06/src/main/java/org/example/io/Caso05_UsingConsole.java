package org.example.io;

import java.io.Console;
import java.io.PrintWriter;

/**
 * CASO 5: Using Console.
 *
 * java.io.Console proporciona acceso a la consola del sistema:
 *   - readLine(): leer entrada del usuario
 *   - readPassword(): leer sin mostrar caracteres (passwords)
 *   - reader(): obtiene el Reader asociado
 *   - writer(): obtiene el PrintWriter asociado
 *
 * System.console() retorna null si no hay consola (ej. IDE sin terminal).
 * Ejecutar desde terminal: java -cp target/classes org.example.io.Caso05_UsingConsole
 */
public class Caso05_UsingConsole {

    public static void main(String[] args) {
        Console c = System.console();
        if (c == null) {
            System.out.println("Console is not supported");
            return;
        }

        PrintWriter out = c.writer();
        out.println("To quit type: exit");
        out.println("Type value and press enter:");

        String txt;
        while (!(txt = c.readLine()).equals("exit")) {
            out.println("Echo: " + txt);
        }
    }
}
