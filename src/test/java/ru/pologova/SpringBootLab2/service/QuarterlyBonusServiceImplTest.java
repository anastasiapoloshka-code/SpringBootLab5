package ru.pologova.SpringBootLab2.service;

import org.junit.jupiter.api.Test;
import ru.pologova.SpringBootLab2.enums.Positions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuarterlyBonusServiceImplTest {

    private final QuarterlyBonusService service =
            new QuarterlyBonusServiceImpl();

    @Test
    void calculateForTL() {

        double result = service.calculate(
                Positions.TL,
                100000.0,
                2.0,
                243,
                2024
        );

        double expected = 100000.0 * 2.0 * 366 * 2.0 / 243 / 4;

        assertEquals(expected, result, 0.000001);
    }

    @Test
    void calculateForCTO() {

        double result = service.calculate(
                Positions.CTO,
                100000.0,
                2.0,
                243,
                2025
        );

        double expected = 100000.0 * 2.0 * 365 * 2.5 / 243 / 4;

        assertEquals(expected, result, 0.000001);
    }

    @Test
    void calculateForDEV() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculate(
                        Positions.DEV, 100000.0, 2.0, 243, 2025
                )
        );
    }

    @Test
    void calculateForHR() {

        assertThrows(
                IllegalArgumentException.class,
                () -> service.calculate(
                        Positions.HR, 100000.0, 2.0, 243, 2025
                )
        );
    }
}