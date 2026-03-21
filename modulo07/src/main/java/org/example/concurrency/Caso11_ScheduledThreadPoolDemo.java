package org.example.concurrency;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Scheduled Thread Pool - Ejecuta tareas con delay y/o periódicamente
 * 
 * Características:
 * - schedule(): ejecuta UNA vez después de un delay
 * - scheduleAtFixedRate(): ejecuta periódicamente (periodo fijo desde inicio)
 * - scheduleWithFixedDelay(): ejecuta periódicamente (delay fijo desde fin)
 * - Pool de threads configurables
 */
public class Caso11_ScheduledThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Scheduled Thread Pool Demo ===\n");
        
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);
        
        // 1. Tarea con delay único
        System.out.println(">>> 1. Tarea con delay de 2 segundos");
        scheduler.schedule(() -> {
            System.out.println("[DELAY] Ejecutada después de 2s");
        }, 2, TimeUnit.SECONDS);
        
        // 2. Tarea periódica con tasa fija (desde inicio de tarea)
        System.out.println(">>> 2. Tarea periódica cada 1 segundo (fixed rate)");
        scheduler.scheduleAtFixedRate(() -> {
            long time = System.currentTimeMillis() % 100000;
            System.out.printf("[%5d ms] [FIXED-RATE] Heartbeat%n", time);
        }, 0, 1, TimeUnit.SECONDS);
        
        // 3. Tarea periódica con delay fijo (desde fin de tarea)
        System.out.println(">>> 3. Tarea periódica con 1s de delay después de completar");
        scheduler.scheduleWithFixedDelay(() -> {
            long time = System.currentTimeMillis() % 100000;
            System.out.printf("[%5d ms] [FIXED-DELAY] Processing...%n", time);
            try {
                Thread.sleep(500); // Simula trabajo de 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, 0, 1, TimeUnit.SECONDS);
        
        // Dejar correr por 6 segundos
        Thread.sleep(6000);
        
        System.out.println("\n>>> Iniciando shutdown...");
        scheduler.shutdown();
        
        if (scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.println("✓ Scheduler detenido");
        } else {
            scheduler.shutdownNow();
            System.out.println("✗ Forzado shutdownNow");
        }
    }
}
