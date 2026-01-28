package pe.joedayz.sobreescritura;

import java.math.BigDecimal;

public class Shop {

    public void order(Product product) {
        BigDecimal price = product.getPrice();
        System.out.println("Price = "
                + price);
    }


    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.order(new Product());
        shop.order(new Food());
        shop.order(new Drink());
    }
}
