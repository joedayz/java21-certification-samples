package org.example.exceptions;

/**
 * Recurso que implementa AutoCloseable para try-with-resources.
 * Simula un recurso cuyos métodos doThings y close pueden lanzar excepciones.
 */
public class SomeResource implements AutoCloseable {

    public void doThings(boolean error) throws Exception {
        if (error) {
            throw new Exception("Action failed");
        }
    }

    @Override
    public void close() throws Exception {
        throw new Exception("Closure failed");
    }
}
