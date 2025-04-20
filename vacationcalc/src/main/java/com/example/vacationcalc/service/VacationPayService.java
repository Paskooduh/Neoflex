package com.example.vacationcalc.service;

import com.example.vacationcalc.util.HolidaysList;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Service
public class VacationPayService {

    private static final BigDecimal AVERAGE_MONTH_DAYS = BigDecimal.valueOf(29.3);
    private static final BigDecimal TAX_RATE = BigDecimal.valueOf(0.13);


    public BigDecimal calculateByDays(BigDecimal averageSalary, int vacationDays) {
        validateInput(averageSalary, vacationDays);
        BigDecimal dailySalary = calculateDailySalary(averageSalary);
        return calculateNetPay(dailySalary.multiply(BigDecimal.valueOf(vacationDays)));
    }

    public BigDecimal calculateByDates(BigDecimal averageSalary, LocalDate start, LocalDate end) {
        validateInput(averageSalary, 0); // Проверяем только зарплату
        int workingDays = countWorkingDays(start, end);
        return calculateByDays(averageSalary, workingDays);
    }

    private int countWorkingDays(LocalDate start, LocalDate end) {
        int count = 0;
        LocalDate current = start;
        System.out.println("Checking days:");

        while (!current.isAfter(end)) {
            boolean working = isWorkingDay(current);
            System.out.printf("%s: %s%n", current, working ? "рабочий" : "выходной");
            if (working) count++;
            current = current.plusDays(1);
        }

        return count;
    }

    private boolean isWorkingDay(LocalDate date) {
        return !isWeekend(date) && !HolidaysList.isHoliday(date);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

    private BigDecimal calculateDailySalary(BigDecimal salary) {
        return salary.divide(AVERAGE_MONTH_DAYS, 10, RoundingMode.HALF_UP)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateNetPay(BigDecimal grossPay) {
        BigDecimal tax = grossPay.multiply(TAX_RATE);
        BigDecimal netPay = grossPay.subtract(tax);
        return netPay.setScale(2, RoundingMode.HALF_UP);
    }

    private void validateInput(BigDecimal salary, int days) {
        if (salary.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной");
        }
        if (days < 0) {
            throw new IllegalArgumentException("Количество дней отпуска не может быть отрицательным");
        }
    }
}