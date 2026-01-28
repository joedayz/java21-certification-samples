package pe.joedayz.initialization;

public class Food extends Product{

    static{
        System.out.println("Static Init Food");
    }

    {
        System.out.println("Instance Init Food");
    }

    public Food() {
        System.out.println("Constructor Food");
    }
}
