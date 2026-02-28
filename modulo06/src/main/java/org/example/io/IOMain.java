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
 *   java -cp target/classes org.example.io.serialization.Caso06_SerializationBasics
 *   java -cp target/classes org.example.io.serialization.Caso07_SerializableObjectGraph
 *   java -cp target/classes org.example.io.serialization.Caso08_ObjectSerialization
 *   java -cp target/classes org.example.io.serialization.Caso09_SerializationSensitiveInfo
 *   java -cp target/classes org.example.io.serialization.Caso10_SerializationVersioning
 *   java -cp target/classes org.example.io.nio.Caso11_WorkingWithFilesystems
 *   java -cp target/classes org.example.io.nio.Caso12_ConstructingPaths
 *   java -cp target/classes org.example.io.nio.Caso13_NavigatingFilesystem
 *   java -cp target/classes org.example.io.nio.Caso14_AnalyzePathProperties
 *   java -cp target/classes org.example.io.nio.Caso15_CreatePaths
 *   java -cp target/classes org.example.io.nio.Caso16_CreateTempFilesFolders
 *   java -cp target/classes org.example.io.nio.Caso17_CopyAndMovePaths
 *   java -cp target/classes org.example.io.nio.Caso18_DeletePaths
 *
 * | Caso | Tema                                | Clases principales                    |
 * |------|-------------------------------------|--------------------------------------|
 * | 01   | Lectura/escritura binaria           | InputStream, OutputStream, byte[]     |
 * | 02   | Lectura/escritura caracteres        | Reader, Writer, FileReader, FileWriter|
 * | 03   | Streams conectados                  | BufferedReader, PrintWriter, chains   |
 * | 04   | Standard Input/Output               | System.in/out/err, Scanner            |
 * | 05   | Using Console                       | Console, readLine(), writer()         |
 * | 05b  | Console readPassword                | Console.readPassword()                |
 * | 06   | Serialization basics                | ObjectOutputStream, ObjectInputStream |
 * | 07   | Serializable object graph          | transient, grafo completo             |
 * | 08   | Object serialization + exceptions  | FileNotFoundException, ClassNotFound  |
 * | 09   | Sensitive info - SHA-256 hash      | ByteArrayOutputStream, MessageDigest  |
 * | 10   | Versioning - serialVersionUID      | InvalidClassException                 |
 * | 11   | Working with Filesystems           | FileSystem, FileStore, roots          |
 * | 12   | Constructing Paths                 | Path.of, resolve, normalize, relativize|
 * | 13   | Navigating Filesystem              | Files.list, walk, symbolic links      |
 * | 14   | Analyze Path Properties            | isDirectory, PosixFileAttributes      |
 * | 15   | Create Paths                       | createDirectories, writeString, lines |
 * | 16   | Temp files/folders                 | createTempDirectory, createTempFile   |
 * | 17   | Copy and Move Paths                | Files.copy, Files.move                |
 * | 18   | Delete Paths                       | walk reverseOrder recursive delete    |
 */
public class IOMain {

    public static void main(String[] args) {
        System.out.println("Ejecuta cada CasoXX_* individualmente para validar.");
    }
}
