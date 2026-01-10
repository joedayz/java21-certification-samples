package pe.joedayz;

import java.math.BigDecimal;

public class OtroProduct {

    final String name = "Tea";
    final BigDecimal price = BigDecimal.ZERO;

//    {
//        //this.name = "Tea";
//    }
//
//    public OtroProduct(){
//        this.name = "Tea";
//    }

    public BigDecimal getDiscount(final BigDecimal discount){
        return price.multiply(discount);
    }


}

class Shop{
    public static void main(String[] args) {
        OtroProduct p = new OtroProduct();

        BigDecimal percentage = BigDecimal.valueOf(0.2);
        final BigDecimal amount = p.getDiscount(percentage);

    }
}
