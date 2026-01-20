package pe.joedayz.localdatetimeapi;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class FormattingAndParsing {

    public static void main(String[] args) throws ParseException {

        // Valores iniciales
        BigDecimal price = BigDecimal.valueOf(2.99);
        Double tax = 0.2;
        int quantity = 12345;

        // Locale
        Locale locale = Locale.of("en", "GB");

        // Formateadores
        NumberFormat currencyFormat =
                NumberFormat.getCurrencyInstance(locale);

        NumberFormat percentageFormat =
                NumberFormat.getPercentInstance(locale);

        NumberFormat numberFormat =
                NumberFormat.getNumberInstance(locale);

        //formateo
        String formattedPrice = currencyFormat.format(price);
        String formattedTax = percentageFormat.format(tax);
        String formattedQuantity = numberFormat.format(quantity);

        System.out.println(formattedPrice);
        System.out.println(formattedTax);
        System.out.println(formattedQuantity);

        // Parsing
        BigDecimal p = BigDecimal.valueOf(
                (Double) currencyFormat.parse("£2.99")
        );

        Double t = (Double) percentageFormat.parse("20%");
        int q = numberFormat.parse("12,345").intValue();

        System.out.println(p);
        System.out.println(t);
        System.out.println(q);

    }
}
