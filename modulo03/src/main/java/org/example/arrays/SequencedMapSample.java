package org.example.arrays;

import java.util.LinkedHashMap;
import java.util.SequencedMap;

public class SequencedMapSample {

    public static void main(String[] args) {
        SequencedMap<Integer, String> map = new LinkedHashMap<>();

        map.put(1, "A");
        map.put(2, "B");
        map.put(3, "C");

        map.putFirst(0, "START");
        map.putLast(4, "END");

        System.out.println(map);

        System.out.println(map.firstEntry()); // 0=START
        System.out.println(map.lastEntry());  // 4=END

        map.pollFirstEntry();
        map.pollLastEntry();

        System.out.println(map);
    }
}
