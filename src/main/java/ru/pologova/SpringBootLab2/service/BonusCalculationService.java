package ru.pologova.SpringBootLab2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.pologova.SpringBootLab2.model.Request;
import ru.pologova.SpringBootLab2.model.Response;

import java.time.Year;

@Slf4j
@Service
public class BonusCalculationService {

    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;

    public BonusCalculationService(
            AnnualBonusService annualBonusService,
            QuarterlyBonusService quarterlyBonusService) {

        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
    }

    public void calculateBonuses(
            Request request,
            Response response) {

        double annualBonus = annualBonusService.calculate(
                request.getPosition(),
                request.getSalary(),
                request.getBonus(),
                request.getWorkDays()
        );

        response.setAnnualBonus(annualBonus);

        log.info("Годовая премия рассчитана: {}", annualBonus);

        if (request.getPosition().isManager()) {

            double quarterlyBonus = quarterlyBonusService.calculate(
                    request.getPosition(),
                    request.getSalary(),
                    request.getBonus(),
                    request.getWorkDays(),
                    Year.now().getValue()
            );

            response.setQuarterlyBonus(quarterlyBonus);

            log.info("Квартальная премия рассчитана: {}", quarterlyBonus);

        } else {
            response.setQuarterlyBonus(null);

            log.info("Квартальная премия не положена сотруднику");
        }
    }
}