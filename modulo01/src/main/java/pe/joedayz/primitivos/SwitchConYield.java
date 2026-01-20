package pe.joedayz.primitivos;

public class SwitchConYield {

    public static void main(String[] args) {
        char status = 'E';
        double cost = 10;
        double price = switch (status){
              case 'S','N' -> cost += 1;
              case 'D' -> {

                  double discount = cost*0.2;
                  cost -= discount;
                  yield cost;
              }
              case 'E' ->  0;
              default -> 3;
        };
        System.out.println(price);
    }
}
