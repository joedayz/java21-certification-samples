package pe.joedayz.localdatetimeapi;

import java.time.LocalDateTime;

public class MoreOperations {

    public static void main(String[] args) {

        LocalDateTime current = LocalDateTime.now();

        LocalDateTime different = current
                .withMinute(14)
                .withDayOfMonth(3)
                .plusHours(12);



        int year = different.getYear();
        int month = different.getMonthValue();
        int day = different.getDayOfMonth();
        System.out.println("Year = " + year);
        System.out.println("Month = " + month);
        System.out.println("Day = " + day);

        boolean before = current.isBefore(different);
        System.out.println("Before = " + before);
    }
}
