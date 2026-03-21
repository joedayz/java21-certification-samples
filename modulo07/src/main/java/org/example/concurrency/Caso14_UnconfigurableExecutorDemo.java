package org.example.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Unconfigurable Executor Service - Wrapper que previene reconfiguración
 * 
 * Características:
 * - Wrapper sobre otro ExecutorService
 * - Previene que se modifique la configuración
 * - Útil cuando pasas un executor a código que no debe cerrarlo
 * - Los métodos shutdown/shutdownNow lanzan UnsupportedOperationException
 */
public class Caso14_UnconfigurableExecutorDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Unconfigurable Executor Service Demo ===\n");
        
        // Crear executor normal
        ExecutorService originalExecutor = Executors.newFixedThreadPool(3);
        
        // Crear wrapper unconfigurable
        ExecutorService unconfigurable = Executors.unconfigurableExecutorService(originalExecutor);
        
        System.out.println(">>> Ejecutando tareas con el wrapper unconfigurable\n");
        
        // Las tareas funcionan normalmente
        for (int i = 1; i <= 5; i++) {
            final int taskId = i;
            unconfigurable.execute(() -> {
                System.out.printf("[Tarea %d] ejecutando en %s%n", 
                    taskId, Thread.currentThread().getName());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        Thread.sleep(1000);
        
        // Intentar shutdown en el wrapper falla
        System.out.println("\n>>> Intentando shutdown() en el wrapper...");
        try {
            unconfigurable.shutdown();
            System.out.println("✗ ERROR: No debería permitir shutdown");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ UnsupportedOperationException - shutdown bloqueado como esperado");
        }
        
        // Intentar shutdownNow en el wrapper también falla
        System.out.println("\n>>> Intentando shutdownNow() en el wrapper...");
        try {
            unconfigurable.shutdownNow();
            System.out.println("✗ ERROR: No debería permitir shutdownNow");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ UnsupportedOperationException - shutdownNow bloqueado como esperado");
        }
        
        // Pero SÍ podemos cerrar el executor original
        System.out.println("\n>>> Cerrando el executor ORIGINAL (no el wrapper)...");
        originalExecutor.shutdown();
        originalExecutor.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("\n✓ Uso típico: pasar executor a código externo sin riesgo de que lo cierren");
    }
}
