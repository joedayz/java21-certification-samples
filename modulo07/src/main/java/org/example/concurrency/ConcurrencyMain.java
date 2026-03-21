package org.example.concurrency;

/**
 * Indice de demos de concurrencia en Java 21.
 *
 * Ejecutar desde modulo07:
 *   mvn -q exec:java
 *
 * Ejecutar un caso especifico:
 *   mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso01_PlatformVsVirtualThreads"
 *   mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso02_ExecutorServiceInvokeAll"
 *   mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso03_ThreadSafeCounter"
 *   mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso04_CompletableFuturePipeline"
 *   mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso05_ProducerConsumerBlockingQueue"
 */
public class ConcurrencyMain {

    public static void main(String[] args) {
        System.out.println("Modulo 07 - Concurrencia Java 21");
        System.out.println("Ejecuta cada CasoXX_* individualmente para ver la demo.");
    }
}

