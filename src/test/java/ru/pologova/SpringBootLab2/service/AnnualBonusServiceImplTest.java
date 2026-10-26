package ru.pologova.SpringBootLab2.service;

import org.junit.jupiter.api.Test;
import ru.pologova.SpringBootLab2.enums.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnnualBonusServiceImplTest {

    private final AnnualBonusService service =
            new AnnualBonusServiceImpl();

    @Test
    void calculate() {

        Positions position = Positions.HR;
        double salary = 100000.0;
        double bonus = 2.0;
        int workDays = 243;

        double result = service.calculate(
                position, salary, bonus, workDays
        );

        double expected = 360493.8271604938;

        assertEquals(expected, result, 0.000001);
    }

    @Test
    void calculateWithInvalidWorkDays() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculate(
                        Positions.HR, 100000.0, 2.0, 0
                )
        );
    }
    @Test
    void calculateLeapYear() {

        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();

        double result = service.calculate(
                Positions.HR,
                100000.0,
                2.0,
                243,
                2024
        );

        double expected = 361481.48148148146;

        assertEquals(expected, result, 0.000001);
    }

    @Test
    void calculateNormalYear() {

        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();

        double result = service.calculate(
                Positions.HR,
                100000.0,
                2.0,
                243,
                2025
        );

        double expected = 360493.8271604938;

        assertEquals(expected, result, 0.000001);
    }
}