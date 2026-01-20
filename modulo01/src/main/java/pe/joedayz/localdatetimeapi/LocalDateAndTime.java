package pe.joedayz.localdatetimeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class LocalDateAndTime {

    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalTime thisTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        LocalDate someDay = LocalDate.of(2019, Month.APRIL, 1);
        LocalTime someTime = LocalTime.of(10, 6);

        LocalDateTime otherDateTime =
                LocalDateTime.of(2019, Month.MARCH, 31, 23, 59);

        LocalDateTime someDateTime = someDay.atTime(someTime);
        System.out.println("SomeDateTime = " + someDateTime);
        LocalDate whatWasTheDate = someDateTime.toLocalDate();
        System.out.println("WhatWasTheDate = " + whatWasTheDate);
    }
}
