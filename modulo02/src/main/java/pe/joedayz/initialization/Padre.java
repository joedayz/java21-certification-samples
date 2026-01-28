package pe.joedayz.initialization;

public class Padre {

    static{
        System.out.println("Static Init Padre");
    }

    {
        System.out.println("Instance Init Padre");
    }

    public Padre() {
        System.out.println("Constructor Padre");
    }
}

class Hijo extends Padre{
    static {
        System.out.println("Static Init Hijo");
    }
    {
        System.out.println("Instance Init Hijo");
    }
    public Hijo() {
        System.out.println("Constructor Hijo");
    }


}
class Main{
    public static void main(String[] args) {
        Hijo hijo = new Hijo();
        Hijo hijo2 = new Hijo();
    }
}