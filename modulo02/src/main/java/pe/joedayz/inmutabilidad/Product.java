package pe.joedayz.inmutabilidad;

import java.math.BigDecimal;

public class Product {

    private static int maxId = 0;
    private final int id;
    private final String name;
    private final BigDecimal price;

    { id = ++maxId; }

    public Product(String name) {
        this.name = name;
        this.price = BigDecimal.ZERO;
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getDiscount(final BigDecimal discount) {
        return price.multiply(discount);
    }
}
