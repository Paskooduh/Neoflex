package com.example.vacationcalc.dto;

import java.math.BigDecimal;

public class VacationPayResponse {
    private final BigDecimal vacationPay;
    private final String message;

    public VacationPayResponse(BigDecimal vacationPay) {
        this.vacationPay = vacationPay.setScale(2, BigDecimal.ROUND_HALF_UP);

        this.message = "Total vacation pay (with NDFL)";
    }

    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public String getMessage() {
        return message;
    }
}