package pe.joedayz.otropaquete;

import pe.joedayz.accesmodifiers.Product;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Product product = new Product();
        //product.name = "Tea"; porque es privado el field name
        //product.price = 1.99; NO SE PUEDE porque estoy en otro paquete

        //product.play(); NO SE PUEDE porque estoy en otro paquete
        //product.discount = BigDecimal.valueOf(0.1);

        Food food = new Food();
        //food.discount = BigDecimal.valueOf(0.1); A pesar que lo heredo no lo puedo usar porque estoy en otro paquete


    }
}
