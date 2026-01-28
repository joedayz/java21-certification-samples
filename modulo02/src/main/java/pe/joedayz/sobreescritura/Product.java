package pe.joedayz.sobreescritura;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {

    private BigDecimal price;

    public BigDecimal getPrice(){
        return price;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                '}';
    }
}
