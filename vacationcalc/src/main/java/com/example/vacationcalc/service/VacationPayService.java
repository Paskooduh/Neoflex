package com.example.vacationcalc.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class VacationPayService {

    public BigDecimal calculateVacationPay(BigDecimal averageSalaryPerYear, int vacationDays) {

        // Проверка на отрицательные значения
        if (averageSalaryPerYear.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Зарплата не может быть отрицательной.");
        }
        if (vacationDays < 0) {
            throw new IllegalArgumentException("Количество дней отпуска не может быть отрицательным.");
        }

        // Расчет средней дневной зарплаты
        BigDecimal dailySalary = averageSalaryPerYear.divide(BigDecimal.valueOf(29.3), 2, BigDecimal.ROUND_HALF_UP);

        // Расчет отпускных
        BigDecimal vacationPay = dailySalary.multiply(BigDecimal.valueOf(vacationDays));

        // Учет НДФЛ (13%)
        BigDecimal taxDeduction = vacationPay.multiply(BigDecimal.valueOf(0.13));

        // Итоговые отпускные после вычета налога
        return vacationPay.subtract(taxDeduction);
    }
}