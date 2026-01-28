package pe.joedayz.abstractas;

public abstract class Product {

    public abstract void serve();

    public void prepare(){
        System.out.println("Preparing " + this);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
