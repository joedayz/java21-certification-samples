package org.example.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Soluciones para evitar Deadlock, Livelock y Starvation
 * 
 * Este ejemplo muestra las técnicas correctas para prevenir problemas de concurrencia:
 * 1. Deadlock: Orden consistente de adquisición de locks
 * 2. Livelock: Backoff aleatorio en reintentos
 * 3. Starvation: Fair locks (FIFO)
 */
public class Caso19_DeadlockSolutionDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Soluciones a Problemas de Concurrencia ===\n");
        
        solucionDeadlock();
        System.out.println("\n" + "=".repeat(60) + "\n");
        solucionLivelock();
        System.out.println("\n" + "=".repeat(60) + "\n");
        solucionStarvation();
    }

    /**
     * SOLUCIÓN DEADLOCK: Adquirir locks siempre en el mismo orden
     */
    private static void solucionDeadlock() throws InterruptedException {
        System.out.println(">>> SOLUCIÓN 1: Prevenir Deadlock\n");
        
        final Object RECURSO_A = new Object();
        final Object RECURSO_B = new Object();

        Thread t1 = new Thread(() -> 
            transferirSeguro(RECURSO_A, RECURSO_B, 100, "Thread-1"));
        Thread t2 = new Thread(() -> 
            transferirSeguro(RECURSO_B, RECURSO_A, 200, "Thread-2"));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\n✓ Sin deadlock - ambos threads completaron");
    }
    
    private static void transferirSeguro(Object from, Object to, int cantidad, String threadName) {
        // Siempre adquirir en orden por identityHashCode (o ID numérico)
        Object primero, segundo;
        if (System.identityHashCode(from) < System.identityHashCode(to)) {
            primero = from;
            segundo = to;
        } else {
            primero = to;
            segundo = from;
        }
        
        synchronized (primero) {
            System.out.println("[" + threadName + "] Adquirió primer lock");
            try { Thread.sleep(50); } catch (InterruptedException e) {}
            
            synchronized (segundo) {
                System.out.println("[" + threadName + "] Adquirió segundo lock");
                System.out.println("[" + threadName + "] ✓ Transferencia completada: $" + cantidad);
            }
        }
    }

    /**
     * SOLUCIÓN LIVELOCK: Backoff aleatorio en reintentos
     */
    private static void solucionLivelock() throws InterruptedException {
        System.out.println(">>> SOLUCIÓN 2: Prevenir Livelock\n");
        
        Lock lockA = new ReentrantLock();
        Lock lockB = new ReentrantLock();

        Thread t1 = new Thread(() -> tareaConBackoff("Alice", lockA, lockB));
        Thread t2 = new Thread(() -> tareaConBackoff("Bob", lockB, lockA));

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("\n✓ Sin livelock - backoff aleatorio permite progreso");
    }
    
    private static void tareaConBackoff(String nombre, Lock primerLock, Lock segundoLock) {
        for (int intento = 0; intento < 5; intento++) {
            if (primerLock.tryLock()) {
                try {
                    Thread.sleep(10);
                    
                    if (segundoLock.tryLock()) {
                        try {
                            System.out.println("[" + nombre + "] ✓ Completó tarea en intento #" + (intento + 1));
                            return;
                        } finally {
                            segundoLock.unlock();
                        }
                    } else {
                        System.out.println("[" + nombre + "] Reintentando con backoff...");
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                } finally {
                    primerLock.unlock();
                }
            }
            
            // Backoff ALEATORIO - cada thread espera diferente
            try {
                int backoffTime = 50 + (int)(Math.random() * 100);
                Thread.sleep(backoffTime);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        System.out.println("[" + nombre + "] ⚠️ No completó");
    }

    /**
     * SOLUCIÓN STARVATION: Usar fair locks (FIFO)
     */
    private static void solucionStarvation() throws InterruptedException {
        System.out.println(">>> SOLUCIÓN 3: Prevenir Starvation\n");
        
        // Lock con fairness = true (orden FIFO)
        Lock recursoJusto = new ReentrantLock(true);
        int[] contadores = new int[6]; // 0=normal, 1-5=VIPs

        Thread normal = new Thread(() -> tareaJusta(0, false, recursoJusto, contadores));
        Thread[] vips = new Thread[5];
        for (int i = 0; i < 5; i++) {
            final int id = i + 1;
            vips[i] = new Thread(() -> tareaJusta(id, true, recursoJusto, contadores));
        }

        normal.start();
        for (Thread vip : vips) {
            vip.start();
        }

        normal.join();
        for (Thread vip : vips) {
            vip.join();
        }

        System.out.println("\n>>> Accesos completados:");
        System.out.println("Usuario Normal: " + contadores[0] + " accesos");
        for (int i = 1; i <= 5; i++) {
            System.out.println("VIP-" + i + ": " + contadores[i] + " accesos");
        }
        
        System.out.println("\n✓ Sin starvation - fair lock garantiza acceso equitativo");
    }
    
    private static void tareaJusta(int id, boolean esVIP, Lock recursoJusto, int[] contadores) {
        for (int i = 0; i < 10; i++) {
            recursoJusto.lock();
            try {
                contadores[id]++;
                String tipo = esVIP ? "VIP-" + id : "Normal";
                if (i % 3 == 0) {
                    System.out.println("[" + tipo + "] Acceso #" + (i + 1));
                }
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } finally {
                recursoJusto.unlock();
            }
        }
    }
}