package org.example.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Livelock - Threads activos pero sin progresar
 * 
 * Escenario: Dos personas intentan cruzarse en un pasillo estrecho.
 * Ambas se mueven hacia el mismo lado al mismo tiempo, una y otra vez,
 * sin lograr pasar. Están "activas" pero no progresan.
 * 
 * Características:
 * - Threads activos (no bloqueados) pero sin progresar
 * - Responden a las condiciones cambiando de estado
 * - Consume CPU (a diferencia del deadlock)
 * - Típicamente ocurre en algoritmos de retry sin backoff
 */
public class Caso17_LivelockDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Livelock Demo ===\n");
        System.out.println("Dos personas intentan cruzarse en un pasillo...\n");

        // Crear dos "personas" con locks para representar su posición
        Persona alice = new Persona("Alice");
        Persona bob = new Persona("Bob");

        // Locks para coordinar (de forma incorrecta)
        Lock lockAlice = new ReentrantLock();
        Lock lockBob = new ReentrantLock();

        // Thread de Alice: intenta pasar cediendo constantemente
        Thread threadAlice = new Thread(() -> {
            int intentos = 0;
            while (intentos < 10) {
                intentos++;
                
                if (lockAlice.tryLock()) {
                    try {
                        System.out.println("[Alice] Intento #" + intentos + ": Voy a pasar...");
                        Thread.sleep(50);
                        
                        // Intenta tomar el lock de Bob (el pasillo)
                        if (lockBob.tryLock()) {
                            try {
                                System.out.println("[Alice] ✓ Logré cruzar el pasillo!");
                                alice.setCruzo(true);
                                return;
                            } finally {
                                lockBob.unlock();
                            }
                        } else {
                            // Bob está en el camino, Alice retrocede
                            System.out.println("[Alice] Bob está ahí, me muevo al lado...");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    } finally {
                        lockAlice.unlock();
                    }
                }
                
                // Pequeña pausa antes de reintentar
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("[Alice] ⚠️ Rendida después de " + intentos + " intentos");
        });

        // Thread de Bob: hace exactamente lo mismo (aquí está el problema)
        Thread threadBob = new Thread(() -> {
            int intentos = 0;
            while (intentos < 10) {
                intentos++;
                
                if (lockBob.tryLock()) {
                    try {
                        System.out.println("[Bob]   Intento #" + intentos + ": Voy a pasar...");
                        Thread.sleep(50);
                        
                        if (lockAlice.tryLock()) {
                            try {
                                System.out.println("[Bob]   ✓ Logré cruzar el pasillo!");
                                bob.setCruzo(true);
                                return;
                            } finally {
                                lockAlice.unlock();
                            }
                        } else {
                            System.out.println("[Bob]   Alice está ahí, me muevo al lado...");
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    } finally {
                        lockBob.unlock();
                    }
                }
                
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("[Bob]   ⚠️ Rendido después de " + intentos + " intentos");
        });

        System.out.println(">>> Iniciando threads...\n");
        threadAlice.start();
        threadBob.start();

        threadAlice.join();
        threadBob.join();

        System.out.println("\n>>> RESULTADO:");
        System.out.println("Alice cruzó: " + alice.isCruzo());
        System.out.println("Bob cruzó: " + bob.isCruzo());
        
        System.out.println("\n⚠️  LIVELOCK OBSERVADO:");
        System.out.println("   Ambos están activos y respondiendo");
        System.out.println("   Pero ninguno logra progresar");
        System.out.println("   Se mueven sincronizadamente evitándose mutuamente");
        System.out.println("\n💡 SOLUCIÓN: Usar backoff aleatorio o prioridades diferentes");
    }

    static class Persona {
        private final String nombre;
        private boolean cruzo = false;

        public Persona(String nombre) {
            this.nombre = nombre;
        }

        public boolean isCruzo() {
            return cruzo;
        }

        public void setCruzo(boolean cruzo) {
            this.cruzo = cruzo;
        }
    }
}
