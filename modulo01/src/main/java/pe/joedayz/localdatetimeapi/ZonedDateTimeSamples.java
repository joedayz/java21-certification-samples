package pe.joedayz.localdatetimeapi;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ZonedDateTimeSamples {

    public static void main(String[] args) {

        ZoneId london = ZoneId.of("Europe/London");
        ZoneId la = ZoneId.of("America/Los_Angeles");

        LocalDateTime someTime =
                LocalDateTime.of(2019, Month.APRIL, 1, 7, 14);

        ZonedDateTime londonTime =
                ZonedDateTime.of(someTime, london);

        System.out.println("London time: "+londonTime);

        ZonedDateTime laTime = londonTime.withZoneSameInstant(la); // 01/04/2019 las 7:14
        System.out.println("LA time: "+laTime); // 23:14 del 31/03/2019

    }
}
