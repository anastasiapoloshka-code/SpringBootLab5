package ru.pologova.SpringBootLab2.service;

import org.springframework.stereotype.Service;
import ru.pologova.SpringBootLab2.enums.Positions;

@Service
public class QuarterlyBonusServiceImpl
        implements QuarterlyBonusService {

    private final AnnualBonusServiceImpl annualBonusService =
            new AnnualBonusServiceImpl();

    @Override
    public double calculate(
            Positions position,
            double salary,
            double bonus,
            int workDays,
            int year) {

        if (position == null || !position.isManager()) {
            throw new IllegalArgumentException(
                    "Квартальная премия доступна только руководителям"
            );
        }

        double annualBonus = annualBonusService.calculate(
                position,
                salary,
                bonus,
                workDays,
                year
        );

        return annualBonus / 4;
    }
}