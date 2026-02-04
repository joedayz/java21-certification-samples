package pe.joedayz.cloneable;

public class Product implements Cloneable{

    private String name;

    public Product(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                '}';
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Product p1 = new Product("Tea");
        Product p2 = (Product) p1.clone();

        System.out.println(p2);
    }
}
