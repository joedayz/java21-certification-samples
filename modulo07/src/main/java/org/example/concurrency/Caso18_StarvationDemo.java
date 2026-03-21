package org.example.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Starvation - Un thread nunca obtiene acceso a recursos compartidos
 * 
 * Escenario: Una impresora compartida donde trabajos prioritarios
 * constantemente se adelantan, dejando trabajos de baja prioridad sin ejecutarse.
 * 
 * Características:
 * - Thread listo para ejecutar pero nunca obtiene CPU/recursos
 * - Causado por scheduling injusto o alta contención
 * - El thread no está bloqueado, solo ignorado
 * - Puede ser causado por prioridades muy desiguales
 */
public class Caso18_StarvationDemo {

    private static final Lock impresora = new ReentrantLock();
    private static volatile int trabajosImpresos = 0;
    private static volatile boolean detener = false;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Starvation Demo ===\n");
        System.out.println("Simulando cola de impresión con prioridades...\n");

        // Thread de baja prioridad (usuario normal)
        Thread usuarioNormal = new Thread(() -> {
            int intentos = 0;
            while (!detener && intentos < 100) {
                intentos++;
                if (impresora.tryLock()) {
                    try {
                        trabajosImpresos++;
                        System.out.println("  [Usuario Normal] ✓ Imprimiendo trabajo #" + intentos);
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    } finally {
                        impresora.unlock();
                    }
                } else {
                    // No obtuvo el lock, esperando
                    if (intentos % 20 == 0) {
                        System.out.println("  [Usuario Normal] ⏳ Esperando... intento #" + intentos);
                    }
                }
                
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("  [Usuario Normal] ⚠️ Terminado con solo " + intentos + " intentos completados");
        }, "Usuario-Normal");

        // Threads de alta prioridad (usuarios VIP) - compitiendo agresivamente
        Thread[] usuariosVIP = new Thread[5];
        for (int i = 0; i < usuariosVIP.length; i++) {
            final int vipId = i + 1;
            usuariosVIP[i] = new Thread(() -> {
                int trabajos = 0;
                while (!detener && trabajos < 20) {
                    if (impresora.tryLock()) {
                        try {
                            trabajosImpresos++;
                            trabajos++;
                            System.out.println("[VIP-" + vipId + "] ⚡ Imprimiendo trabajo prioritario #" + trabajos);
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        } finally {
                            impresora.unlock();
                        }
                    }
                    
                    // VIPs reintentan muy rápido
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }, "VIP-" + vipId);
            
            // Dar prioridad más alta a VIPs
            usuariosVIP[i].setPriority(Thread.MAX_PRIORITY);
        }

        // Dar prioridad baja al usuario normal
        usuarioNormal.setPriority(Thread.MIN_PRIORITY);

        System.out.println(">>> Iniciando usuario normal (baja prioridad)...");
        usuarioNormal.start();
        
        Thread.sleep(200); // Dejar que intente un poco
        
        System.out.println(">>> Iniciando 5 usuarios VIP (alta prioridad y reintentos rápidos)...\n");
        for (Thread vip : usuariosVIP) {
            vip.start();
            Thread.sleep(50);
        }

        // Ejecutar por 3 segundos
        Thread.sleep(3000);
        detener = true;

        // Esperar a que terminen
        usuarioNormal.join(1000);
        for (Thread vip : usuariosVIP) {
            vip.join(1000);
        }

        System.out.println("\n>>> RESULTADO:");
        System.out.println("Total trabajos impresos: " + trabajosImpresos);
        System.out.println("Estado Usuario Normal: " + usuarioNormal.getState());
        
        System.out.println("\n⚠️  STARVATION OBSERVADO:");
        System.out.println("   El usuario normal tiene dificultad para acceder a la impresora");
        System.out.println("   Los VIPs monopolizan el recurso por sus reintentos agresivos");
        System.out.println("   El usuario normal está 'listo' pero raramente ejecuta");
        System.out.println("\n💡 SOLUCIÓN: Usar fairness (ReentrantLock(true)) o evitar tryLock en loops cerrados");
    }
}
