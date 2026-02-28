package org.example.exceptions;

/**
 * Índice de ejemplos de excepciones - ejecutar cada clase por separado:
 *
 * Desde terminal (desde raíz modulo05):
 *   java -cp target/classes org.example.exceptions.Caso01_CustomException
 *
 * O con Maven:
 *   mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso01_CustomException"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso02_ThrowingExceptions"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso03_CatchingExceptions"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso04_ExecutionFlowAndStackTrace"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso05_NullPointerExceptionHelpful"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso06_UncheckedArithmeticException"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso07_CheckedExceptionOpenFile"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso08_HandlingWithLogging"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso09_TryWithResources"
 * mvn exec:java -Dexec.mainClass="org.example.exceptions.Caso10_SuppressedExceptions"
 *
 * O desde el IDE: Run > Run 'CasoXX_...main()'
 *
 * | Caso | Tema                                      | Resultado al ejecutar                    |
 * |------|-------------------------------------------|------------------------------------------|
 * | 01   | Custom Exception (ProductException)       | Muestra 3 constructores y chaining       |
 * | 02   | Throwing checked vs unchecked             | Captura IOException, ProductException    |
 * | 03   | Catching: multi-catch, orden, finally     | Muestra orden correcto de catches        |
 * | 04   | Flujo y stack trace (divide por cero)     | Termina con ArithmeticException          |
 * | 05   | NPE con mensajes útiles Java 14+          | Termina con NPE descriptivo              |
 * | 06   | ArithmeticException explícita             | Termina con ArithmeticException          |
 * | 07   | Checked: openFile con NoSuchFileException  | Captura y muestra stack trace            |
 * | 08   | Handling: logging, rethrow, cleanup        | Captura FileNotFoundException/IOException|
 * | 09   | try-with-resources                        | Auto-cierre, FileNotFoundException       |
 * | 10   | Suppressed exceptions                     | Muestra getSuppressed()                  |
 */
public class ExcepcionesMain {

    public static void main(String[] args) {
        System.out.println("Ejecuta cada CasoXX_* individualmente para validar.");
        System.out.println("Ejemplo: mvn exec:java -Dexec.mainClass=\"org.example.exceptions.Caso01_CustomException\"");
    }
}
