package org.example.arrays;

import java.util.ArrayList;
import java.util.SequencedCollection;

public class SequencedCollectionSample {

    public static void main(String[] args) {
        SequencedCollection<String> list = new ArrayList<>();

        list.add("A");
        list.add("B");
        list.add("C");

        list.addFirst("START");
        list.addLast("END");

        System.out.println(list);
        // [START, A, B, C, END]

        System.out.println(list.getFirst()); // START
        System.out.println(list.getLast());  // END

        list.removeFirst();
        list.removeLast();

        System.out.println(list);
        // [A, B, C]

        // recorrer al revés
        for (String s : list.reversed()) {
            System.out.print(s);
        }
    }
}
