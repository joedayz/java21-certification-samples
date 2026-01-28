package pe.joedayz.initialization;

public class Shop {

    static{
        System.out.println("Static Init Shop");
    }

    public static void main(String[] args) {
        Product p1 = new Food();
        Product p2 = new Food();
    }
}
