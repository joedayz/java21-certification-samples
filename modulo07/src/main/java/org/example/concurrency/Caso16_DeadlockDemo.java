package org.example.concurrency;

/**
 * Deadlock - Bloqueo mutuo donde threads esperan unos por otros indefinidamente
 * 
 * Escenario: Dos personas intentan cruzar una puerta estrecha desde direcciones opuestas.
 * Cada una necesita que la otra se retire primero, pero ninguna lo hace.
 * 
 * Características:
 * - Dos o más threads bloqueados permanentemente
 * - Cada uno esperando un recurso que el otro tiene
 * - Ningún progreso posible sin intervención externa
 * - Condiciones necesarias: Exclusión mutua, Hold and Wait, No Preemption, Circular Wait
 */
public class Caso16_DeadlockDemo {

    // Dos recursos que causarán el deadlock
    private static final Object RECURSO_A = new Object();
    private static final Object RECURSO_B = new Object();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Deadlock Demo ===\n");
        System.out.println("Simulando transferencia bancaria entre dos cuentas...\n");

        // Thread 1: Intenta transferir de A a B
        Thread thread1 = new Thread(() -> {
            transferir("Thread-1", RECURSO_A, RECURSO_B, 100);
        }, "Thread-1");

        // Thread 2: Intenta transferir de B a A (orden inverso = deadlock)
        Thread thread2 = new Thread(() -> {
            transferir("Thread-2", RECURSO_B, RECURSO_A, 200);
        }, "Thread-2");

        System.out.println(">>> Iniciando threads...\n");
        thread1.start();
        Thread.sleep(100); // Pequeño delay para asegurar que thread1 tome RECURSO_A
        thread2.start();

        // Esperar un tiempo para demostrar el deadlock
        thread1.join(3000);
        thread2.join(3000);

        System.out.println("\n>>> ANÁLISIS:");
        System.out.println("Thread-1 state: " + thread1.getState() + " (esperando RECURSO_B)");
        System.out.println("Thread-2 state: " + thread2.getState() + " (esperando RECURSO_A)");
        System.out.println("\n⚠️  DEADLOCK DETECTADO:");
        System.out.println("   Thread-1 tiene RECURSO_A y espera RECURSO_B");
        System.out.println("   Thread-2 tiene RECURSO_B y espera RECURSO_A");
        System.out.println("   Ninguno puede progresar - círculo vicioso\n");

        // Forzar interrupción para salir
        thread1.interrupt();
        thread2.interrupt();
        
        System.out.println("💡 SOLUCIÓN: Siempre adquirir locks en el mismo orden");
    }

    private static void transferir(String threadName, Object origen, Object destino, int cantidad) {
        System.out.println("[" + threadName + "] Intentando adquirir lock en " + 
            (origen == RECURSO_A ? "RECURSO_A" : "RECURSO_B"));
        
        synchronized (origen) {
            System.out.println("[" + threadName + "] ✓ Adquirió lock en " + 
                (origen == RECURSO_A ? "RECURSO_A" : "RECURSO_B"));
            
            // Simular algo de procesamiento
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            
            System.out.println("[" + threadName + "] Intentando adquirir lock en " + 
                (destino == RECURSO_A ? "RECURSO_A" : "RECURSO_B"));
            
            // Aquí ocurre el deadlock
            synchronized (destino) {
                System.out.println("[" + threadName + "] ✓ Adquirió lock en " + 
                    (destino == RECURSO_A ? "RECURSO_A" : "RECURSO_B"));
                System.out.println("[" + threadName + "] Transfiriendo $" + cantidad);
                System.out.println("[" + threadName + "] Transferencia completada");
            }
        }
    }
}
