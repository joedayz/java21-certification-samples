package pe.joedayz.primitivos;

public class OperadoresPrecedencia {

    public static void main(String[] args) {
        int a = 1, b = 0;

        System.out.println(a++);

        System.out.println(a);

        System.out.println(++b);

        float f = (float)15.5;

        byte x = 127, y=5;

        //byte z = x + y; NO COMPILA PORQUE SE PROMOVIO EL X+Y  A INT

        int w = x + y;

        byte s = (byte)(x + y);

        int r = x/y;

        System.out.println(" r = " + r);

        float p = x/y;

        System.out.println(" p = " + p);

        float p1 = (float)(x/y);

        System.out.println(" p1 = " + p1);

        float p2 = (float)x/y;

        System.out.println(" p2 = " + p2);

        float p3 = x/(float)y;

        System.out.println(" p3 = " + p3);

        x =  (byte)(x + 1);

        //byte -128 a 127

        System.out.println( "x = " + x);

        System.out.println(x++); // El casting lo hace internamente

        char m = 'x';
        char n = ++m;

        System.out.println( "n = " + n);
    }
}
