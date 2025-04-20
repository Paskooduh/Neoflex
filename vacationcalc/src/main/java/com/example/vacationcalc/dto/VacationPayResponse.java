package com.example.vacationcalc.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VacationPayResponse {
    private final BigDecimal vacationPay;
    private final String message;

    public VacationPayResponse(BigDecimal vacationPay) {

        if (vacationPay == null) {
            throw new IllegalArgumentException("Параметр vacationPay не может быть null");
        }


        this.vacationPay = vacationPay.setScale(2, RoundingMode.HALF_UP);
        this.message = "Total vacation pay (with NDFL)";
    }


    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public String getMessage() {
        return message;
    }
}