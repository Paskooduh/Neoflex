package com.example.vacationcalc;

import com.example.vacationcalc.controller.VacationPayController;
import com.example.vacationcalc.service.VacationPayService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VacationcalcApplicationTests {

	@Autowired
	private VacationPayController vacationPayController;

	@MockBean
	private VacationPayService vacationPayService;

	@Test
	void contextLoads() {

		assertThat(vacationPayController).isNotNull();
	}

	@Test
	void testCalculateVacationPay() {

		BigDecimal averageSalary = BigDecimal.valueOf(60000);
		int vacationDays = 10;
		BigDecimal expectedVacationPay = BigDecimal.valueOf(2047.95); // Ожидаемая сумма отпускных

		Mockito.when(vacationPayService.calculateVacationPay(averageSalary, vacationDays))
				.thenReturn(expectedVacationPay);

		var response = vacationPayController.calculateVacationPay(averageSalary, vacationDays);

		assertThat(response.getVacationPay()).isEqualTo(expectedVacationPay);
	}
}