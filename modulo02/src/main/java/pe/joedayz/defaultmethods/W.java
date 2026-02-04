package pe.joedayz.defaultmethods;

public abstract class W {
    public void a(){
        System.out.println("a");
    }
    public abstract void b();
}

interface X1{
    public default void a(){}
    public default void b(){}
    public default void c(){}
}

interface X2 extends X1{
    public default void c(){
        System.out.println("c");
    }
}

class Z extends W implements X2{

    @Override
    public void b() {
        System.out.println("b");
    }

    public static void main(String[] args) {
        Z z = new Z();
        z.b();
        z.a();
        z.c();
    }
}