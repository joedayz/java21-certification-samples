package pe.joedayz.accesmodifiers;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Product product = new Product();
        //product.name = "Tea"; porque es privado el field name
        product.price = 1.99;
        product.discount = BigDecimal.valueOf(0.1);
        product.play();
    }
}
