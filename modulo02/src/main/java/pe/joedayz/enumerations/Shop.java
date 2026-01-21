package pe.joedayz.enumerations;

//import static pe.joedayz.enumerations.Condition.HOT;

public class Shop {
    public static void main(String[] args) {
        Product tea = new Product("Tea", Condition.HOT);

        tea.serve();

        Condition c = Condition.COLD;

        Condition d;
    }
}
