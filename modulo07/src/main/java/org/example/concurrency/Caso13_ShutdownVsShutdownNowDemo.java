package org.example.concurrency;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demuestra la diferencia entre shutdown() y shutdownNow()
 * 
 * shutdown():
 * - NO acepta nuevas tareas
 * - Permite que tareas en ejecución y en cola se completen
 * - Cierre "graceful"
 * 
 * shutdownNow():
 * - NO acepta nuevas tareas
 * - Intenta interrumpir tareas en ejecución
 * - Devuelve lista de tareas que estaban en cola
 * - Cierre "forzado"
 */
public class Caso13_ShutdownVsShutdownNowDemo {

    public static void main(String[] args) throws InterruptedException {
        demoShutdown();
        System.out.println("\n" + "=".repeat(60) + "\n");
        demoShutdownNow();
    }

    private static void demoShutdown() throws InterruptedException {
        System.out.println("=== Demo 1: shutdown() - Cierre Ordenado ===\n");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Enviar 6 tareas (2 se ejecutarán, 4 esperarán en cola)
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[Tarea %d] INICIO en %s%n", taskId, threadName);
                
                try {
                    Thread.sleep(2000); // Tarea larga
                } catch (InterruptedException e) {
                    System.out.printf("[Tarea %d] INTERRUMPIDA%n", taskId);
                    Thread.currentThread().interrupt();
                    return;
                }
                
                System.out.printf("[Tarea %d] COMPLETADA%n", taskId);
            });
        }
        
        // Pequeña pausa para que algunas tareas inicien
        Thread.sleep(500);
        
        System.out.println("\n>>> Llamando shutdown()...");
        executor.shutdown(); // Cierre ordenado
        
        System.out.println(">>> Esperando a que todas las tareas terminen...\n");
        
        if (executor.awaitTermination(15, TimeUnit.SECONDS)) {
            System.out.println("\n✓ RESULTADO: Todas las 6 tareas se completaron");
        } else {
            System.out.println("\n✗ Timeout");
        }
    }

    private static void demoShutdownNow() throws InterruptedException {
        System.out.println("=== Demo 2: shutdownNow() - Cierre Forzado ===\n");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Enviar 6 tareas
        for (int i = 1; i <= 6; i++) {
            final int taskId = i;
            executor.execute(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.printf("[Tarea %d] INICIO en %s%n", taskId, threadName);
                
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.printf("[Tarea %d] INTERRUMPIDA ⚠️%n", taskId);
                    Thread.currentThread().interrupt();
                    return;
                }
                
                System.out.printf("[Tarea %d] COMPLETADA%n", taskId);
            });
        }
        
        // Pequeña pausa
        Thread.sleep(500);
        
        System.out.println("\n>>> Llamando shutdownNow()...");
        List<Runnable> pendingTasks = executor.shutdownNow(); // Cierre forzado
        
        System.out.println(">>> Tareas que estaban en cola (no ejecutadas): " 
            + pendingTasks.size());
        System.out.println(">>> Las tareas en ejecución recibirán interrupt...\n");
        
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("\n✓ RESULTADO: Solo ~2 tareas se ejecutaron, " 
            + pendingTasks.size() + " quedaron en cola, "
            + "~2 fueron interrumpidas");
    }
}
