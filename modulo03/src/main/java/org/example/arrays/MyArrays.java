package org.example.arrays;

public class MyArrays {

    static void main() {
     int[][] matrix = { {0, 4}, {1, 2,3}};

     for (int[] row : matrix) {
         for (int element : row) {
             System.out.print(element + " ");
         }
         System.out.println();
     }
    }
}
