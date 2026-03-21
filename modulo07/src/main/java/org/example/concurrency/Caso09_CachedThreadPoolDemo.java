package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Cached Thread Pool - Crea threads según demanda
 * 
 * Características:
 * - Crea nuevos threads según necesidad
 * - Reutiliza threads que quedan libres (60s timeout)
 * - Ideal para muchas tareas cortas y asíncronas
 * - Puede crecer sin límite (cuidado con recursos)
 */
public class Caso09_CachedThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Cached Thread Pool Demo ===\n");
        
        ExecutorService executor = Executors.newCachedThreadPool();
        
        // Primera ráfaga: 5 tareas simultáneas
        System.out.println(">>> Primera ráfaga: 5 tareas simultáneas");
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.printf("[Tarea %d] ejecutando en %s%n", 
                    taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        // Esperar que terminen
        Thread.sleep(1000);
        System.out.println("\n>>> Segunda ráfaga: reutilización de threads");
        
        // Segunda ráfaga: debería reutilizar threads
        for (int i = 6; i <= 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.printf("[Tarea %d] ejecutando en %s%n", 
                    taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        System.out.println("\n>>> Observa que reutiliza los threads de la primera ráfaga\n");
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("✓ Completado");
    }
}
