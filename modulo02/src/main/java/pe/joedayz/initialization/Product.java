package pe.joedayz.initialization;

public class Product {

    static{
        System.out.println("Static Init Product");
    }

    {
        System.out.println("Instance Init Product");
    }

    public Product() {
        System.out.println("Constructor Product");
    }
}
