package org.example.coleccionesconcurrencia;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SynchronizedSample {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Cake", 1);
        map.put("Cookie", 2);
        map.put("Tea", 3);

        // Crear mapa sincronizado
        Map<String, Integer> syncMap = Collections.synchronizedMap(map);

        syncMap.put("Coffee", 4);     // OK, adquiere lock
        syncMap.get("Cake");           // OK, adquiere lock


        // PERO para iteración necesitas sincronizar manualmente:
        synchronized (syncMap) {
            for (Map.Entry<String, Integer> entry : syncMap.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        }

    }
}
