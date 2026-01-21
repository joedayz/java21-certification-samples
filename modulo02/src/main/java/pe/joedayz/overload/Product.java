package pe.joedayz.overloadsamples;


import java.math.BigDecimal;

public class Product {
    private BigDecimal price;
    private BigDecimal discount = BigDecimal.ZERO;
    public void setPrice(double price){
        this.price = BigDecimal.valueOf(price);
    }

//    public BigDecimal setPrice(String myprice){
//        this.price = BigDecimal.valueOf(Double.valueOf(myprice));
//        return this.price;
//    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public void setPrice(BigDecimal price, BigDecimal discount){
        this.price = price;
        this.discount = discount;
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setPrice(1.99);
        product.setPrice(BigDecimal.valueOf(1.99));
        product.setPrice(BigDecimal.valueOf(1.99), BigDecimal.valueOf(0.1));
    }
}
