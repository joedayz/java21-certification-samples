package pe.joedayz.herencia;

public class Food extends Product{

    private String name;


    public Food() {
        super("abc");
    }

    @Override
    public String getName() {
        return "get name de food";
    }

    @Override
    public void setName(String name) {
        System.out.println("set de food");
    }

    public void buzz(){
        System.out.printf("Buzz solo le pertenece a food");
    };
}
