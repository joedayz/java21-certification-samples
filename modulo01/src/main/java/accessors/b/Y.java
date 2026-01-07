package accessors.b;

import accessors.a.X;

public class Y extends X {

    public void doThings(){
        Y p = new Y();
        //p.m4 = "Hello";
        //p.m3 = "Hello";
        p.m2 = "Hello";

        p.m1 = "Hello";

        //NUNCA

        X  x = new X();
        System.out.println(x.m1);
        //System.out.println(x.m2);
    }
}
