package org.example.arrays;

import java.util.LinkedHashSet;
import java.util.SequencedSet;

public class SequencedSetSample {

    public static void main(String[] args) {
        SequencedSet<String> set = new LinkedHashSet<>();

        set.add("A");
        set.add("B");
        set.add("C");

        set.addFirst("START");
        set.addLast("END");

        System.out.println(set);
        // [START, A, B, C, END]

        System.out.println(set.getFirst()); // START
        System.out.println(set.getLast());  // END

    }
}
