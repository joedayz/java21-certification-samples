package pe.joedayz;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

public class ProductStatic {

    private static Period defaultExpiryPeriod = Period.ofDays(3);

    private String name;
    private BigDecimal price;
    private LocalDate bestBefore;


    public static void setDefaultExpiryPeriod(int days){
        defaultExpiryPeriod = Period.ofDays(days);
    }

    public static void main(String[] args) {
        ProductStatic p1 = new ProductStatic();
        p1.setName("Cake");
        p1.setPrice(BigDecimal.valueOf(2.99));
        p1.setBestBefore(LocalDate.of(2019,04, 11));

        p1.setDefaultExpiryPeriod(5); // si es posible

        ProductStatic.setDefaultExpiryPeriod(7); // FORMA RECOMENDADA

        ProductStatic p2 = new ProductStatic();
        p2.setName("Cookie");
        p2.setPrice(BigDecimal.valueOf(1.99));
        p2.setBestBefore(LocalDate.of(2019,04, 11));

        System.out.println(p2.getDefaultExpiryPeriod()); //NO RECOMENDADA. PERO POSIBLE
        System.out.println(ProductStatic.getDefaultExpiryPeriod()); //FORMA RECOMENDADA

    }


    public static Period getDefaultExpiryPeriod() {
        return defaultExpiryPeriod;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(LocalDate bestBefore) {
        this.bestBefore = bestBefore;
    }
}
