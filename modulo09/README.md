# Modulo 07 - Concurrencia (Java 21)

Proyecto de demos practicas para repasar concurrencia con Java 21:

- Threads de plataforma vs virtuales.
- `ExecutorService` con tareas `Callable`.
- Sincronizacion con `synchronized`.
- Pipelines con `CompletableFuture`.
- Patron producer-consumer con `BlockingQueue`.
- Ciclo de vida de un `Thread`.
- Interrupcion de hilos con `interrupt()`.

## Requisitos

- Java 21
- Maven 3.9+

## Comandos rapidos

```bash
cd /Users/josediaz/Projects/JoeDayz/java21-certification-samples/modulo07
mvn test
mvn -q exec:java
```

## Ejecutar casos individuales

```bash
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso01_PlatformVsVirtualThreads"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso02_ExecutorServiceInvokeAll"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso03_ThreadSafeCounter"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso04_CompletableFuturePipeline"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso05_ProducerConsumerBlockingQueue"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso06_ThreadLifecycleStates"
mvn -q exec:java -Dexec.mainClass="org.example.concurrency.Caso07_ThreadInterruptDemo"
```
