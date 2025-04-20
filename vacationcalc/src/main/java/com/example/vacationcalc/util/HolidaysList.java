package com.example.vacationcalc.util;


import java.time.LocalDate;
import java.util.Set;

public class HolidaysList {
    private static final Set<LocalDate> HOLIDAYS = Set.of(

            LocalDate.of(2024, 1, 1),
            LocalDate.of(2024, 1, 2),
            LocalDate.of(2024, 1, 3),
            LocalDate.of(2024, 1, 4),
            LocalDate.of(2024, 1, 5),
            LocalDate.of(2024, 1, 6),
            LocalDate.of(2024, 1, 7),
            LocalDate.of(2024, 1, 8),

            LocalDate.of(2024, 2, 23),

            LocalDate.of(2024, 3, 8),

            LocalDate.of(2024, 5, 1),
            LocalDate.of(2024, 5, 9),

            LocalDate.of(2024, 6, 12),

            LocalDate.of(2024, 11, 4)
            //fsd
    );

    public static boolean isHoliday(LocalDate date) {
        return HOLIDAYS.contains(date);
    }
}