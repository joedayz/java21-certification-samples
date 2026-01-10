package pe.joedayz;

import java.math.BigDecimal;
import java.time.LocalDate;

import static java.lang.Math.random;

public class ShopStatic {

    public static void main(String[] args) {
        Math.round(1.99);
        double value = random();
        System.out.println(value);

        BigDecimal price = BigDecimal.valueOf(1.99);
        LocalDate actualDate = LocalDate.now();

        System.out.println("Hello world");

    }

}
