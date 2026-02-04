package pe.joedayz.interfaces;

public class Drink implements Consumable, Liquid{
    @Override
    public int measure() {
        return 0;
    }

    @Override
    public void consume(int quantity) {

    }

    public static void main(String[] args) {
        Drink d = new Drink();
        d.prepare(); //heredas el metodo default
    }
}
