package com.example.vacationcalc.dto;

import java.math.BigDecimal;

public class VacationPayResponse {
    private BigDecimal vacationPay; // Сумма отпускных

    public BigDecimal getVacationPay() {
        return vacationPay;
    }


    public void setVacationPay(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

}