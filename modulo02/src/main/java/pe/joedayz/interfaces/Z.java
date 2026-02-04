package pe.joedayz.interfaces;

public class Z extends  W implements X, Y{
    @Override
    public void a() {

    }

    @Override
    public void b() {

    }

    public static void main(String[] args) {
        Z z = new Z();
        z.a();
        z.b();
        z.e();
        X.d();
        Y.d();
        //Z.d();
    }
}
