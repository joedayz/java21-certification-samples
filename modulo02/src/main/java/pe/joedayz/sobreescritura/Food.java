package pe.joedayz.sobreescritura;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Food extends Product{

    private LocalDate bestBefore;

    @Override
    public BigDecimal getPrice() {

        BigDecimal discount = (bestBefore.isEqual(LocalDate.now().plusDays(1))
                ? super.getPrice().multiply(BigDecimal.valueOf(0.1))
                : BigDecimal.ZERO);

        return super.getPrice().subtract(discount);
    }



}
