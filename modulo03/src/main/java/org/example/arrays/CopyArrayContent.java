package org.example.arrays;

import java.util.Arrays;

public class CopyArrayContent {

    public static void main(String[] args) {
        char[] a1 = { 'a','c','m','e'};

        char[] a2 = { 't', 'o', '\0', '\0', '\0' }; // no puedes tener ''

        System.arraycopy(a1, 2 ,a2, 3, 2);

        System.out.println(a2);

        char[] b1 = {'a','c','m','e'};
        char[] b2 = Arrays.copyOf(b1, 5);

        System.out.println(b2);

    }
}
