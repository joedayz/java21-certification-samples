package pe.joedayz.primitivos;

public class SwitchExample {

    public static void main(String[] args) {
        char status = 'S';

        double price = 10;

        switch (status){
            case 'S':
                price +=1;
            case 'N':
                price +=2;

            case 'D':
                price -=4;
                break;
            case 'E':
                price = 0;
                break;
            default:
                price=3;
        }

        System.out.println("Price = "+ price);
    }
}
