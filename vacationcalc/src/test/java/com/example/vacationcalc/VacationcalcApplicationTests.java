package com.example.vacationcalc.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VacationPayServiceTest {

	@Autowired
    private VacationPayService service;

	@Test
    void testCalculateByDates_WithHolidays() {
        // Период 1-8 января (все дни праздничные)
        LocalDate start = LocalDate.of(2024, 1, 1);
        LocalDate end = LocalDate.of(2024, 1, 8); // Исправлено!
        BigDecimal result = service.calculateByDates(BigDecimal.valueOf(100000), start, end);
        assertEquals(BigDecimal.ZERO.setScale(2), result);
	}

	@Test
    void testCalculateByDates_WorkingDaysOnly() {
        LocalDate start = LocalDate.of(2024, 5, 13);
        LocalDate end = LocalDate.of(2024, 5, 17);
        BigDecimal result = service.calculateByDates(BigDecimal.valueOf(100000), start, end);
        assertEquals(BigDecimal.valueOf(14846.42), result);
    }

    @Test
    void testCountWorkingDays_MixedPeriod() {
        LocalDate start = LocalDate.of(2024, 5, 8);
        LocalDate end = LocalDate.of(2024, 5, 12);
        BigDecimal result = service.calculateByDates(BigDecimal.valueOf(100000), start, end);


        assertEquals(5938, result.intValue());
	}
}