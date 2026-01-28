package pe.joedayz.herencia;

public class Drink extends Product{

    private String name;

    public Drink(String name){
        super(name);
        System.out.println("abc");
    }

    @Override
    public String getName() {
        return "get name de drink";
    }

    @Override
    public void setName(String name) {
        System.out.println("set de drink");
    }
}
