package org.example.arrays;

public class BreakAndContinue {


    public static void main(String[] args) {
        char[][] matrix = {{'A', 'B', 'C', 'D', 'E'},
                {'F', 'G', 'H', 'I', 'J'}
                , {'K', 'L', 'M', 'N', 'O'},
                {'P', 'Q', 'R', 'S', 'T'}
                , {'U', 'V', 'W', 'X', 'Y'}};

        StringBuilder sb = new StringBuilder();

        OUTER:
        for (char[] row : matrix) {
            for (char c : row) {
                if(c=='C') continue;
                if(c=='H') continue OUTER;
                if(c =='N') break;
                if(c=='S') break OUTER;
                sb.append(c);
            }
            sb.append("\n");
       }

        System.out.println(sb);
    }

}
