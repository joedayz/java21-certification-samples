package org.example.concurrency;

public class Caso07_ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        demoInterruptDuringRunning();
        System.out.println();
        demoInterruptDuringSleep();
    }

    private static void demoInterruptDuringRunning() throws InterruptedException {
        System.out.println("=== Demo 1: interrupt en RUNNABLE ===");

        Runnable cpuTask = () -> {
            long iterations = 0;
            Thread current = Thread.currentThread();

            while (!current.isInterrupted()) {
                iterations++;
                if (iterations % 5_000_000 == 0) {
                    System.out.println("[" + current.getName() + "] trabajando... iterations=" + iterations);
                }
            }

            System.out.println("[" + current.getName() + "] detecto interrupt y termina de forma cooperativa");
        };

        Thread worker = new Thread(cpuTask, "cpu-worker");
        System.out.println("Antes de start -> state=" + worker.getState());
        worker.start();

        Thread.sleep(120);
        System.out.println("Antes de interrupt -> state=" + worker.getState() + ", alive=" + worker.isAlive());
        worker.interrupt();

        worker.join();
        System.out.println("Despues de join -> state=" + worker.getState() + ", alive=" + worker.isAlive());
    }

    private static void demoInterruptDuringSleep() throws InterruptedException {
        System.out.println("=== Demo 2: interrupt en TIMED_WAITING (sleep) ===");

        Runnable sleepingTask = () -> {
            Thread current = Thread.currentThread();
            try {
                System.out.println("[" + current.getName() + "] va a dormir 5s");
                Thread.sleep(5_000);
                System.out.println("[" + current.getName() + "] desperto sin interrupcion");
            } catch (InterruptedException e) {
                // Restablece la bandera si el flujo superior necesita consultarla.
                current.interrupt();
                System.out.println("[" + current.getName() + "] InterruptedException capturada, interrupcion=" + current.isInterrupted());
            }

            System.out.println("[" + current.getName() + "] fin de run() -> TERMINATED");
        };

        Thread sleeper = new Thread(sleepingTask, "sleep-worker");
        sleeper.start();

        Thread.sleep(150);
        System.out.println("Antes de interrupt -> state=" + sleeper.getState());
        sleeper.interrupt();

        sleeper.join();
        System.out.println("Despues de join -> state=" + sleeper.getState() + ", alive=" + sleeper.isAlive());
    }
}

