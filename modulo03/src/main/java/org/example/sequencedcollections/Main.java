package org.example.sequencedcollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");

        String[] array = new String[2];
        array = list.toArray(array);

        System.out.println(Arrays.toString(array));

        list.removeIf(new RemoveB());

        System.out.println(list);
    }


}

class RemoveB implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.equals("B");
    }
}
