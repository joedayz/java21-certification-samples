package org.example.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysSort {

    public static void main(String[] args) {
        String[] names1 = { "Mary", "Ann", "Jane", "Tom"};
        String[] names2 = { "Mary", "Ann", "John", "Tom"};

        boolean isTheSames = Arrays.equals(names1, names2);
        System.out.println("Son iguales? "+ isTheSames);

        Arrays.sort(names2);
        System.out.println(Arrays.toString(names2));

        Arrays.sort(names2, new LengthCompare());
        System.out.println(Arrays.toString(names2));
    }
}

class LengthCompare implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        if(s1.length()>s2.length()) { return 1;}
        else if(s1.length()<s2.length()) { return -1;}
        return 0;
    }
}