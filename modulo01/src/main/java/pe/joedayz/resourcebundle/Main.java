package pe.joedayz.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        Locale locale = Locale.of("en", "GB");
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        String helloPattern = bundle.getString("hello");
        String otherMessage = bundle.getString("other");

        System.out.println("HelloPattern => " + helloPattern);
        System.out.println("OtherMessage => " + otherMessage);
    }
}
