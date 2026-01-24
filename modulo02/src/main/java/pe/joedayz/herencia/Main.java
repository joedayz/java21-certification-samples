package pe.joedayz.herencia;

public class Main {

    public static void main(String[] args) {

        //Product product = new Employee(); //no tienen relacion alguna

        Product p = new Food();  // CP = CH
        System.out.println(p.getName() ); //1. es ver si el metodo el de la izquierda
        //System.out.println(p.buzz()); //2. No compila. porque buzz no le pertenece a Product

        Food food = new Food();
        System.out.println(food.getName());

        Drink drink = new Drink();
        System.out.println(drink.getName());

    }
}
