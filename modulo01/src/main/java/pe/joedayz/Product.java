package pe.joedayz;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Product extends Object{

    private int id;
    private String name;
    private BigDecimal price;
    private LocalDate bestBefore = LocalDate.now().plusDays(3);

    public static final int MAX_EXPIRY_PERIOD = 5;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        if(name == null){
            String dummy = "Unknown";
            return dummy;
        }
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = BigDecimal.valueOf(price);
    }

    public String consume(){
        String feedback = "Good!";
        return feedback;
    }

    public void someOperation(int param){
        var value1 = "Hello";
        var value2 = param;

        for (var i = 0; i < 10; i++) {
            System.out.println(i);
            //TODO implementar esto
        }

    }
}

class ProductMain{
    public static  void main(String[] args) {
        Product p1 = new Product();
        Product p2 = new Product();
        Product p3 = p2;
        p1.setName("Tea");
        p2.setName("Cake");

        System.out.println(p1.getName() + " en una taza");
        System.out.println(p2.getName() + " en un plato");
        System.out.println(p3.getName() + " para compartir");
        p1.setName("Coffee");
        System.out.println(p1.getName() + " en una taza");

        System.out.println(Product.MAX_EXPIRY_PERIOD);

    }
}
