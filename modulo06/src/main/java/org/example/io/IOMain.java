package org.example.io;

/**
 * Índice de ejemplos java.io / java.nio.
 *
 * Ejecutar cada CasoXX individualmente:
 *   java -cp target/classes org.example.io.Caso01_BinaryData
 *   java -cp target/classes org.example.io.Caso02_CharacterData
 *   java -cp target/classes org.example.io.Caso03_ConnectingStreams
 *   java -cp target/classes org.example.io.Caso04_StandardInputOutput
 *   java -cp target/classes org.example.io.Caso05_UsingConsole
 *   java -cp target/classes org.example.io.Caso05b_ConsoleReadPassword
 *
 * | Caso | Tema                                | Clases principales                    |
 * |------|-------------------------------------|--------------------------------------|
 * | 01   | Lectura/escritura binaria           | InputStream, OutputStream, byte[]     |
 * | 02   | Lectura/escritura caracteres        | Reader, Writer, FileReader, FileWriter|
 * | 03   | Streams conectados                  | BufferedReader, PrintWriter, chains   |
 * | 04   | Standard Input/Output               | System.in/out/err, Scanner            |
 * | 05   | Using Console                       | Console, readLine(), writer()         |
 * | 05b  | Console readPassword                | Console.readPassword()                |
 */
public class IOMain {

    public static void main(String[] args) {
        System.out.println("Ejecuta cada CasoXX_* individualmente para validar.");
    }
}
