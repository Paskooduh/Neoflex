package com.example.vacationcalc.controller;

import com.example.vacationcalc.dto.VacationPayResponse;
import com.example.vacationcalc.service.VacationPayService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
@RestController
public class VacationPayController {

    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping("/calculate")
    public VacationPayResponse calculateVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalary,
            @RequestParam(value = "vacationDays", required = false) Integer vacationDays,
            @RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        validateInput(vacationDays, startDate, endDate);

        BigDecimal result = (vacationDays != null)
                ? vacationPayService.calculateByDays(averageSalary, vacationDays)
                : vacationPayService.calculateByDates(averageSalary, startDate, endDate);

        return new VacationPayResponse(result);
    }

    private void validateInput(Integer vacationDays, LocalDate startDate, LocalDate endDate) {
        if (vacationDays == null && (startDate == null || endDate == null)) {
            throw new IllegalArgumentException("Должны быть указаны либо дни отпуска, либо даты начала/окончания");
        }
        if (vacationDays != null && (startDate != null || endDate != null)) {
            throw new IllegalArgumentException("Нельзя одновременно указывать дни и даты отпуска");
        }
        if (startDate != null && endDate != null && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Дата начала отпуска не может быть позже даты окончания");
        }
    }
}