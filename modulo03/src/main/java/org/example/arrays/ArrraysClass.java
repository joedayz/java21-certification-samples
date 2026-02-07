package org.example.arrays;

import java.util.Arrays;
import java.util.Collections;

public class ArrraysClass {

    public static void main(String[] args) {
        String[] values = new String[5];
        Arrays.fill(values, 2, 4, "aaa");

        System.out.println(Arrays.toString(values));

        int x = Arrays.binarySearch(values, "aaa");

        System.out.println("x = "+ x);

        int[] numbers = {1,3,5,7};

        int y = Arrays.binarySearch(numbers, 6);

        System.out.println("y = "+ y);

        int[] desordenados = {9,1,2,6};

        Arrays.sort(desordenados);
        System.out.println(Arrays.toString(desordenados));

        int z = Arrays.binarySearch(desordenados, 9);

        System.out.println("z = "+ z);
    }
}
