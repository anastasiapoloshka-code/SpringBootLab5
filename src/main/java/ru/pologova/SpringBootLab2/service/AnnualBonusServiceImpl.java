package ru.pologova.SpringBootLab2.service;

import org.springframework.stereotype.Service;
import ru.pologova.SpringBootLab2.enums.Positions;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

   @Override
    public double calculate(
            Positions position,
            double salary,
            double bonus,
            int workDays) {

        int currentYear = Year.now().getValue();

        return calculate(
                position, salary, bonus, workDays, currentYear
        );
    }

    public double calculate(
            Positions position,
            double salary,
            double bonus,
            int workDays,
            int year) {

        if (workDays <= 0) {
            throw new IllegalArgumentException(
                    "Количество рабочих дней должно быть больше 0"
            );
        }

        int daysInYear = Year.isLeap(year) ? 366 : 365;

        return salary * bonus * daysInYear
                * position.getPositionCoefficient()
                / workDays;
    }
}