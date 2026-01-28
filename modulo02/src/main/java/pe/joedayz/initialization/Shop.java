package pe.joedayz.initialization;

public class Shop {

    static{
        System.out.println("Static Init Shop");
    }

    {
        System.out.println("Instance Init Shop");
    }


    public static void main(String[] args) {
        Product p1 = new Food(); //inicializacion de objeto
        Product p2 = new Food(); //inicializacion de objeto

    }
}
