package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Fixed Thread Pool - Reutiliza un número fijo de threads
 * 
 * Características:
 * - Pool con tamaño fijo de threads
 * - Las tareas en exceso esperan en cola
 * - Ideal cuando conoces la carga máxima de concurrencia
 */
public class Caso08_FixedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Fixed Thread Pool Demo ===\n");
        
        // Crear pool de SOLO 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(3);
        
        System.out.println("Enviando 10 tareas a un pool de 3 threads...\n");
        
        // Enviar 10 tareas (más que threads disponibles)
        for (int i = 1; i <= 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[Tarea %2d] Iniciada en %s%n", taskId, threadName);
                
                try {
                    // Simular trabajo
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.printf("[Tarea %2d] Completada en %s%n", taskId, threadName);
            });
        }
        
        System.out.println("\n>>> Observa que solo hay 3 threads trabajando simultáneamente\n");
        
        // Shutdown ordenado
        executor.shutdown();
        
        if (executor.awaitTermination(15, TimeUnit.SECONDS)) {
            System.out.println("\n✓ Todas las tareas completadas");
        } else {
            System.out.println("\n✗ Timeout - forzando shutdown");
            executor.shutdownNow();
        }
    }
}
