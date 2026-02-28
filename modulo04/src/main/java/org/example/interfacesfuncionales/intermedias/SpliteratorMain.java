package org.example.interfacesfuncionales.intermedias;

import java.util.Random;
import java.util.Spliterator;

public class SpliteratorMain {

    static void main() {


        /*

        Spliterator: Es como un Iterator pero con superpoderes para procesamiento paralelo.
        La palabra viene de Split + Iterator (iterador que se puede dividir).
        Un Iterator normal recorre elementos uno por uno, en un solo hilo. Un Spliterator
        puede partirse en dos para que dos hilos trabajen cada mitad al mismo tiempo.

        tryAdvance(): Procesa el siguiente elemento si existe (como hasNext() + next() juntos)
        forEachRemaining(): Procesa todos los elementos restantes (como while(hasNext()) completo)
        trySplit(): Divide el Spliterator en dos partes para procesamiento paralelo

        Una vez procesado un elemento, ya no esta disponible.

         */

        // 1. Crear 10 números aleatorios entre 0 y 9, obtener su Spliterator
        Spliterator<Integer> s1 = new Random().ints(10, 0, 10).spliterator();

        // 2. Procesar UN solo elemento (el primero) e imprimirlo
        s1.tryAdvance(v -> System.out.print(v));


        // 3. Intentar dividir s1 en dos mitades
        Spliterator<Integer> s2 = s1.trySplit();


        if (s2 == null) {
            // No se pudo dividir (muy pocos elementos)
            System.out.println("Did not split");
        } else {
            // Se dividió: s1 tiene una mitad, s2 tiene la otra
            s1.forEachRemaining(v -> System.out.print(v));  // Imprimir mitad 1
            s2.forEachRemaining(v -> System.out.print(v));  // Imprimir mitad 2
        }

        /*
        Primer elemento: 7
s1 (mitad 1): 3 8 1 5
s2 (mitad 2): 2 9 4 6
         */
    }
}
