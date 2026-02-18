package org.example.interfacesfuncionales.bi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.ObjDoubleConsumer;
import java.util.function.ObjIntConsumer;
import java.util.function.ObjLongConsumer;

public class BiConsumerMain {

    static void main() {

        BiConsumer<String, Integer> imprimir = (s, n) -> System.out.println(s + ": " + n);
        imprimir.accept("edad", 25);  // imprime "edad: 25"

        Map<String, Integer> map = new HashMap<>();
        BiConsumer<String, Integer> ponerEnMapa = map::put;
        ponerEnMapa.accept("a", 1);
        System.out.println(">" + map + "<");


        ObjIntConsumer<String> prefijoYNumero = (s, n) -> System.out.println(s + " = " + n);
        prefijoYNumero.accept("count", 42);  // "count = 42"

        ObjLongConsumer<StringBuilder> appendLong = (sb, l) -> sb.append(l);
        StringBuilder sb = new StringBuilder("id:");
        appendLong.accept(sb, 12345L);  // sb = "id:12345"

        ObjDoubleConsumer<List<Double>> agregar = (list, d) -> list.add(d);
        List<Double> list = new ArrayList<>();
        agregar.accept(list, 3.14);  // list contiene 3.14
    }
}
