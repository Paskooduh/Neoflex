package com.example.vacationcalc.controller;

import com.example.vacationcalc.dto.VacationPayResponse;
import com.example.vacationcalc.service.VacationPayService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class VacationPayController {

    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping("/calculate")
    public VacationPayResponse calculateVacationPay(
            @RequestParam("averageSalary") BigDecimal averageSalaryPerYear,
            @RequestParam("vacationDays") int vacationDays) {

        // Используем сервис для расчета отпускных
        BigDecimal vacationPay = vacationPayService.calculateVacationPay(averageSalaryPerYear, vacationDays);

        VacationPayResponse response = new VacationPayResponse();
        response.setVacationPay(vacationPay);
        return response;
    }
}