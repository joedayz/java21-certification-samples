package pe.joedayz.varargs;

import java.math.BigDecimal;

public class Product {

    private BigDecimal price;
    private BigDecimal discount;
    private BigDecimal tax;

    public void setFiscalDetails(double... values) {
        switch (values.length) {
            case 3:
                tax = BigDecimal.valueOf(values[2]);
            case 2:
                discount = BigDecimal.valueOf(values[1]);
            case 1:
                price = BigDecimal.valueOf(values[0]);
        }
    }

    public static void main(String... args) {
        Product p = new Product();
        p.setFiscalDetails();
        p.setFiscalDetails(1.99);
        p.setFiscalDetails(1.99, 0.9, 0.1);
        p.setFiscalDetails(1.99, 0.9);
    }
}
