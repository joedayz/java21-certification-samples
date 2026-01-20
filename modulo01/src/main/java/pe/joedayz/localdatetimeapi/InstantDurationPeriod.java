package pe.joedayz.localdatetimeapi;

import java.time.*;

public class InstantDurationPeriod {

    public static void main(String[] args) {


        LocalDate today = LocalDate.now(); // 19 enero 2026
        LocalDate foolsDay = LocalDate.of(2019, Month.APRIL, 1);

        Instant timeStamp = Instant.now(); // basado en el reloj UTC : 2026-01-19T10:30:12.255191Z
        int nanoSecondsFromLastSecond = timeStamp.getNano(); // 255191000
        // han pasado 255.191 milisegundos dentro del segundo actual
        System.out.println("nanoSecondsFromLastSecond = " + nanoSecondsFromLastSecond);
        System.out.println("Segundosd desde 1/1/1970 " + timeStamp.getEpochSecond());

        Period howLong = Period.between(foolsDay, today); //6 años, 9 meses y 18 días
        System.out.println("howLong = " + howLong); //P6Y9M18D  P : period, 6Y -> 6 años, 9M --> 9 meses, 18D --> 18 dias

        Duration twoHours = Duration.ofHours(2);
        System.out.println("twoHours = " + twoHours); //PT2H  P : period, T : time, 2H -> 2 horas

        long seconds = twoHours.minusMinutes(15).getSeconds();
        System.out.println("seconds = " + seconds);

        int days = howLong.getDays();
        System.out.println("days = " + days);
    }
}
