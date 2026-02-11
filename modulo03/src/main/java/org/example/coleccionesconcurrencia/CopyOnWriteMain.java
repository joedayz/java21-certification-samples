package org.example.coleccionesconcurrencia;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteMain {

    public static void main(String[] args) {


        List<String> list = new ArrayList<>();
        list.add("Cake");
        list.add("Cookie");

        List<String> copyOnWriteList = new CopyOnWriteArrayList<>(list);

        copyOnWriteList.add("Tea");     // Copia todo + agrega "Tea"
        copyOnWriteList.add("Coffee");  // Copia todo + agrega "Coffee"

        System.out.println(copyOnWriteList); // [Cake, Cookie, Tea, Coffee]

        for (String item : copyOnWriteList) {
            System.out.println(item);
            // Incluso si otro hilo modifica la lista,
            // este iterador ve el "snapshot" del momento en que se creó
        }
    }
}
