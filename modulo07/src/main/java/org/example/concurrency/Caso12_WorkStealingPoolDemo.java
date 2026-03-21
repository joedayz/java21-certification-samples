package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Work Stealing Pool - Optimizado para paralelismo con work-stealing
 * 
 * Características:
 * - Usa ForkJoinPool internamente
 * - Cada thread tiene su propia cola de tareas
 * - Threads ociosos "roban" tareas de otros threads
 * - Por defecto usa # de procesadores disponibles
 * - Ideal para tareas recursivas o paralelas
 */
public class Caso12_WorkStealingPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Work Stealing Pool Demo ===\n");
        
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("Procesadores disponibles: " + processors);
        
        // Crea pool con paralelismo = # procesadores
        ExecutorService executor = Executors.newWorkStealingPool();
        
        System.out.println("\n>>> Enviando tareas con duración variable\n");
        
        // Tareas con diferentes duraciones para demostrar work-stealing
        for (int i = 1; i <= 12; i++) {
            final int taskId = i;
            final int sleepTime = (i % 3 == 0) ? 1000 : 300; // Algunas más lentas
            
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[Tarea %2d] INICIO en %-30s (durará %dms)%n", 
                    taskId, threadName, sleepTime);
                
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                System.out.printf("[Tarea %2d] FIN en %-30s%n", 
                    taskId, threadName);
            });
        }
        
        System.out.println("\n>>> Threads rápidos 'robarán' tareas de threads ocupados\n");
        
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("\n✓ Completado");
    }
}
