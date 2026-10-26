
package ru.pologova.SpringBootLab2.service;

import ru.pologova.SpringBootLab2.enums.Positions;

public interface AnnualBonusService {

    double calculate(
            Positions position,
            double salary,
            double bonus,
            int workDays
    );
}